package com.dinoproo.legendsawaken.jurassic.entity.custom;

import com.dinoproo.legendsawaken.entity.custom.LegendsEntity;
import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.Arrays;
import java.util.Comparator;

public class BRCEntity extends LegendsEntity {
    public BRCEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

        this.moveControl = new MoveControl(this) {
            @Override
            public void tick() {
                if (this.state == State.MOVE_TO) {
                    double dx = this.targetX - BRCEntity.this.getX();
                    double dz = this.targetZ - BRCEntity.this.getZ();
                    double dy = this.targetY - BRCEntity.this.getY();
                    double distSq = dx * dx + dy * dy + dz * dz;

                    if (distSq < 2.5E-7) {
                        this.state = State.WAIT;
                        BRCEntity.this.setMovementSpeed(0);
                        return;
                    }

                    float targetYaw = (float)(MathHelper.atan2(dz, dx) * (180F / Math.PI)) - 90.0F;
                    BRCEntity.this.setYaw(this.wrapDegrees(BRCEntity.this.getYaw(), targetYaw, 6)); // 🔧 gira suave
                    BRCEntity.this.bodyYaw = BRCEntity.this.getYaw();

                    float speed = (float)(this.speed * BRCEntity.this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    BRCEntity.this.setMovementSpeed(speed);
                } else {
                    BRCEntity.this.setMovementSpeed(0);
                }
            }
        };
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        if (!this.isBaby()) {
            return EntityDimensions.fixed(6f, 14f);
        } else {
            return EntityDimensions.fixed(1.5f, 3.5f);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isBaby()) {
            this.setBoundingBox(new Box(
                    this.getX() - 3.0, this.getY(), this.getZ() - 3.0,
                    this.getX() + 3.0, this.getY() + 14.0, this.getZ() + 3.0
            ));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 150)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 3)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 0.1);
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
    }

    @Override
    protected int getXpToDrop() {
        return 32 + this.getWorld().random.nextInt(3);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.MUTTON);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        BRCEntity child = JurassicEntities.BRACHIOSAURUS.create(world);

        if (entity instanceof BRCEntity parent2) {
            int parent1Variant = this.getVariant().getId();
            int parent2Variant = parent2.getVariant().getId();

            int chosenVariant;

            if ((parent1Variant >= 1 && parent1Variant <= 2) && (parent2Variant >= 1 && parent2Variant <= 2)) {
                chosenVariant = 1 + world.getRandom().nextInt(2);
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
                        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.brachiosaurus.walk"));
                    } else {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.brachiosaurus.idle"));
                    }
                })
        );

        controllers.add(new AnimationController<>(this, "attack_controller", 0, state -> {
            if (this.isAttacking()) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("animation.brachiosaurus.attack"));
            }
            return PlayState.STOP;
        }));
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
        JPIII_A(3, "jpiii_a"),
        JPIII_B(4, "jpiii_b");

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
