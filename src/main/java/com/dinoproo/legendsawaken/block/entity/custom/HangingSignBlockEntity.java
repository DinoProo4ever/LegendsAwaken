package com.dinoproo.legendsawaken.block.entity.custom;

import com.dinoproo.legendsawaken.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

public class HangingSignBlockEntity extends SignBlockEntity {
    public HangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.HANGING_SIGN_BE, blockPos, blockState);
    }

    @Override
    public int getTextLineHeight() {
        return 9;
    }

    @Override
    public int getMaxTextWidth() {
        return 60;
    }

    @Override
    public SoundEvent getInteractionFailSound() {
        return SoundEvents.BLOCK_HANGING_SIGN_WAXED_INTERACT_FAIL;
    }
}
