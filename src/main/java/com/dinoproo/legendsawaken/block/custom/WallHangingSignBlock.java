package com.dinoproo.legendsawaken.block.custom;

import com.dinoproo.legendsawaken.block.entity.custom.HangingSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class WallHangingSignBlock extends net.minecraft.block.WallHangingSignBlock {
    public WallHangingSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings.sounds(woodType.hangingSignSoundType()));
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HangingSignBlockEntity(pos, state);
    }
}
