package com.dinoproo.legendsawaken.jurassic.block.entity;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.CultivatorBlockEntity;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.DNAEnhancerBlockEntity;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.DNAExtractorBlockEntity;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.DNAHybridizerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JurassicBlockEntities {
    public static final BlockEntityType<DNAExtractorBlockEntity> DNA_EXTRACTOR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"dna_extractor_be"),
                    BlockEntityType.Builder.create(DNAExtractorBlockEntity::new, JurassicBlocks.DNA_EXTRACTOR). build(null));

    public static final BlockEntityType<DNAEnhancerBlockEntity> DNA_ENHANCER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"dna_enhancer_be"),
                    BlockEntityType.Builder.create(DNAEnhancerBlockEntity::new, JurassicBlocks.DNA_ENHANCER). build(null));

    public static final BlockEntityType<DNAHybridizerBlockEntity> DNA_HYBRIDIZER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"dna_hybridizer_be"),
                    BlockEntityType.Builder.create(DNAHybridizerBlockEntity::new, JurassicBlocks.DNA_HYBRIDIZER). build(null));

    public static final BlockEntityType<CultivatorBlockEntity> CULTIVATOR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendsAwaken.MOD_ID,"cultivator_be"),
                    BlockEntityType.Builder.create(CultivatorBlockEntity::new, JurassicBlocks.CULTIVATOR). build(null));

    public static void registerBlockEntities() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Blocks Block Entities for " + LegendsAwaken.MOD_ID);
    }
}
