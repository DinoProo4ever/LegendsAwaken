package com.dinoproo.legendsawaken.block.entity;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.block.entity.custom.GiantFernBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<GiantFernBlockEntity> GIANT_FERN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"giant_fern_be"),
                    BlockEntityType.Builder.create(GiantFernBlockEntity::new, ModBlocks.GIANT_FERN).build(null));

    public static void registerBlockEntities() {
        LegendsAwaken.LOGGER.info("Registering Core Block Entities for " + LegendsAwaken.MOD_ID);
    }
}
