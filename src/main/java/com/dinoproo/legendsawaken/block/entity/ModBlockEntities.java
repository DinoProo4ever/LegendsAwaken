package com.dinoproo.legendsawaken.block.entity;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.block.entity.custom.GiantFernBlockEntity;
import com.dinoproo.legendsawaken.block.entity.custom.HangingSignBlockEntity;
import com.dinoproo.legendsawaken.block.entity.custom.SignBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<SignBlockEntity> SIGN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"sign_be"),
                    BlockEntityType.Builder.create(
                            SignBlockEntity::new, ModBlocks.SEQUOIA_SIGN, ModBlocks.SEQUOIA_WALL_SIGN
                    ).build(null));
    public static final BlockEntityType<HangingSignBlockEntity> HANGING_SIGN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"hanging_sign_be"),
                    BlockEntityType.Builder.create(
                            HangingSignBlockEntity::new, ModBlocks.SEQUOIA_HANGING_SIGN, ModBlocks.SEQUOIA_WALL_HANGING_SIGN
                    ).build(null));

    public static final BlockEntityType<GiantFernBlockEntity> GIANT_FERN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"giant_fern_be"),
                    BlockEntityType.Builder.create(GiantFernBlockEntity::new, ModBlocks.GIANT_FERN).build(null));

    public static void registerBlockEntities() {
        LegendsAwaken.LOGGER.info("Registering Core Block Entities for " + LegendsAwaken.MOD_ID);
    }
}
