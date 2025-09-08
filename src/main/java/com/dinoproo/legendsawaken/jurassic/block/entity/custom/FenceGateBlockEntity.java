package com.dinoproo.legendsawaken.jurassic.block.entity.custom;

import com.dinoproo.legendsawaken.jurassic.block.custom.FenceGateBlock;
import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class FenceGateBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public FenceGateBlockEntity(BlockPos pos, BlockState state) {
        super(JurassicBlockEntities.FENCE_GATE_BE, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        BlockState blockState = this.getCachedState();

        if (blockState.getBlock() instanceof FenceGateBlock) {
            boolean open = blockState.get(FenceGateBlock.OPEN);

            state.getController().setAnimation(RawAnimation.begin()
                    .thenPlayAndHold(open ? "animation.fence_gate.open" : "animation.fence_gate.close"));

            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
