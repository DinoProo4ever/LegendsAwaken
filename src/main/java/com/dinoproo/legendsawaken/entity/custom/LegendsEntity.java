package com.dinoproo.legendsawaken.entity.custom;

import com.dinoproo.legendsawaken.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

import java.util.UUID;

public class LegendsEntity extends AnimalEntity implements GeoEntity, Angerable {
    private boolean permanentBaby = false;

    public EscapeDangerGoal lowHealthPanicGoal;
    private boolean panicAdded = false;

    private int timeSinceLastDamage = 0;
    private static final int REGEN_DELAY_TICKS = 600;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    protected static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(LegendsEntity.class, TrackedDataHandlerRegistry.INTEGER);

    protected LegendsEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    private UUID angryAt;
    private int angerTime;
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 40);
    private static final UniformIntProvider ANGER_PASSING_COOLDOWN_RANGE = TimeHelper.betweenSeconds(4, 6);
    private int angerPassingCooldown;

    @Override
    protected void mobTick() {
        this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        if (this.getTarget() != null) {
            this.tickAngerPassing();
        }

        this.checkLowHealthPanic();
        this.handleRegenAfterDelay();
    }

    private void checkLowHealthPanic() {
        if (lowHealthPanicGoal == null) return;

        boolean isLowHealth = this.getHealth() / this.getMaxHealth() < 0.2F;

        if (isLowHealth && !panicAdded) {
            this.goalSelector.add(1, lowHealthPanicGoal);
            panicAdded = true;
        } else if (!isLowHealth && panicAdded) {
            this.goalSelector.remove(lowHealthPanicGoal);
            panicAdded = false;
        }
    }

    private void handleRegenAfterDelay() {
        if (this.getHealth() < this.getMaxHealth()) {
            timeSinceLastDamage++;
            if (timeSinceLastDamage >= REGEN_DELAY_TICKS && age % 100 == 0) {
                this.heal(1F);
            }
        }
    }

    private void tickAngerPassing() {
        if (this.angerPassingCooldown > 0) {
            this.angerPassingCooldown--;
        } else {
            if (this.getVisibilityCache().canSee(this.getTarget())) {
                this.angerNearbyMobs();
            }

            this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
        }
    }

    private void angerNearbyMobs() {
        double d = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        Box box = Box.from(this.getPos()).expand(d, 10.0, d);
        this.getWorld()
                .getEntitiesByClass(LegendsEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR)
                .stream()
                .filter(entity -> entity != this)
                .filter(entity -> entity.getTarget() == null)
                .filter(entity -> !entity.isTeammate(this.getTarget()))
                .forEach(entity -> entity.setTarget(this.getTarget()));
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(ModItems.GROWTH_INHIBITOR)) {
            if (this.isBaby() && !permanentBaby) {
                permanentBaby = true;
                player.sendMessage(Text.literal(this.getName().getString())
                        .copy().append(Text.translatable("event.legendsawaken.inhibit")), true);
                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.literal(this.getName().getString())
                        .copy().append(Text.translatable("event.legendsawaken.already_inhibited")), true);
                return ActionResult.FAIL;
            }
        }

        if (itemStack.isOf(ModItems.GROWTH_SERUM)) {
            if (permanentBaby) {
                permanentBaby = false;
                player.sendMessage(Text.literal(this.getName().getString())
                        .copy().append(Text.translatable("event.legendsawaken.remove_inhibit")), true);
                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.literal(this.getName().getString())
                        .copy().append(Text.translatable("event.legendsawaken.not_inhibited")), true);
                return ActionResult.FAIL;
            }
        }

        return super.interactMob(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isBaby() && permanentBaby) {
            this.setBaby(true);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
        nbt.putBoolean("PermanentBaby", permanentBaby);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
        permanentBaby = nbt.getBoolean("PermanentBaby");
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean result = super.damage(source, amount);
        if (result) {
            timeSinceLastDamage = 0;
        }
        return result;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    protected int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }
}
