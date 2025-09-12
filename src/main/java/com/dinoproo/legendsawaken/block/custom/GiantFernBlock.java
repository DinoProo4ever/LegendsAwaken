package com.dinoproo.legendsawaken.block.custom;

import com.dinoproo.legendsawaken.block.entity.custom.GiantFernBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GiantFernBlock extends TallFlowerBlock implements BlockEntityProvider {
    public GiantFernBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return new GiantFernBlockEntity(pos, state);
        }
        return null;
    }
}
