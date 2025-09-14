package com.dinoproo.legendsawaken.block.custom;

import com.dinoproo.legendsawaken.block.entity.custom.HangingSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class HangingSignBlock extends net.minecraft.block.HangingSignBlock {
    public HangingSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings.sounds(woodType.hangingSignSoundType()));
        this.setDefaultState((((this.stateManager.getDefaultState()).with(ROTATION, 0)).with(ATTACHED, false)).with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HangingSignBlockEntity(pos, state);
    }
}
