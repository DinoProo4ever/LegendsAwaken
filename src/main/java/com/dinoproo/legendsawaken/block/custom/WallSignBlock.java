package com.dinoproo.legendsawaken.block.custom;

import com.dinoproo.legendsawaken.block.entity.custom.SignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class WallSignBlock extends net.minecraft.block.WallSignBlock {
    public WallSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings.sounds(woodType.soundType()));
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SignBlockEntity(pos, state);
    }
}
