package com.dinoproo.legendsawaken.jurassic.entity.custom;

import com.dinoproo.legendsawaken.entity.custom.LegendsEntity;
import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.*;

import java.util.Arrays;
import java.util.Comparator;

public class VLCEntity extends LegendsEntity {
    public VLCEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        if (!this.isBaby()) {
            return EntityDimensions.fixed(0.875f, 1.7f);
        } else {
            return EntityDimensions.fixed(0.35f, 0.68f);
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0.3)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.5)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, -0.5)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 0.5);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        lowHealthPanicGoal = new EscapeDangerGoal(this, 1.5);

        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D,
                Ingredient.ofItems(Items.MUTTON, Items.PORKCHOP, Items.BEEF, Items.CHICKEN), false));

        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4F));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.375D, false));

        this.targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(1, (new UniversalAngerGoal<>(this, true)));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SheepEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, MerchantEntity.class, false));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, CowEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, PigEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
    }

    @Override
    protected int getXpToDrop() {
        return 4 + this.getWorld().random.nextInt(3);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.MUTTON);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        VLCEntity child = JurassicEntities.VELOCIRAPTOR.create(world);

        if (entity instanceof VLCEntity parent2) {
            int parent1Variant = this.getVariant().getId();
            int parent2Variant = parent2.getVariant().getId();

            int chosenVariant;

            if ((parent1Variant >= 0 && parent1Variant <= 2) && (parent2Variant >= 0 && parent2Variant <= 2)) {
                chosenVariant = world.getRandom().nextInt(3);
            }
            else if ((parent1Variant >= 3 && parent1Variant <= 5) && (parent2Variant >= 3 && parent2Variant <= 5)) {
                chosenVariant = 3 + world.getRandom().nextInt(3);
            }
            else if (parent1Variant == parent2Variant) {
                chosenVariant = parent1Variant;
            }
            else {
                chosenVariant = world.getRandom().nextBoolean() ? parent1Variant : parent2Variant;
            }

            assert child != null;
            child.setVariant(Variant.byId(chosenVariant));
        }

        return child;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(this, "controller", 5, event -> {
                    if (event.isMoving()) {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.velociraptor.walk"));
                    } else {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.velociraptor.idle"));
                    }
                })
        );

        controllers.add(
                new AnimationController<>(this, "attack_controller", 0, state -> {
                    if (this.isAttacking()) {
                        return state.setAndContinue(RawAnimation.begin().thenPlay("animation.velociraptor.attack"));
                    }
                    return PlayState.STOP;
                })
        );
    }

    public Variant getVariant() {
        return Variant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(Variant variant) {
        this.dataTracker.set(LegendsEntity.DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Variant variant = Util.getRandom(Variant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
    
    public enum Variant {
        JP(0, "jp"),
        TLW_A(1, "tlw_a"),
        TLW_B(2, "tlw_b"),
        JPIII_A(3, "jpiii_a"),
        JPIII_B(4, "jpiii_b"),
        JPIII_C(5, "jpiii_c"),
        JW_A(6, "jw_a"),
        JW_B(7, "jw_b"),
        JW_C(8, "jw_c"),
        JW_D(9, "jw_d"),
        R(10, "r");

        private static final Variant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
                comparingInt(Variant::getId)).toArray(Variant[]::new);
        private final int id;
        private final String name;

        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public static Variant byId(int id) {
            return BY_ID[id % BY_ID.length];
        }
    }
}
