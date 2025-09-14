package com.dinoproo.legendsawaken.block.entity.custom;

import com.dinoproo.legendsawaken.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class SignBlockEntity extends net.minecraft.block.entity.SignBlockEntity {
    public SignBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.SIGN_BE, pos, state);
    }

    public SignBlockEntity(BlockEntityType blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }
}
