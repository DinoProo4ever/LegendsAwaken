package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {
    public ModBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.REINFORCED_GLASS)

                .add(ModBlocks.STEEL_BLOCK)
                .add(ModBlocks.STEEL_STAIRS)
                .add(ModBlocks.STEEL_SLAB)
                .add(ModBlocks.STEEL_DOOR)
                .add(ModBlocks.STEEL_TRAPDOOR)

                .add(JurassicBlocks.AMBER_BLOCK_XS)
                .add(JurassicBlocks.AMBER_BLOCK_S)
                .add(JurassicBlocks.AMBER_BLOCK_M)
                .add(JurassicBlocks.AMBER_BLOCK_L)
                .add(JurassicBlocks.AMBER_BLOCK_XL)

                .add(JurassicBlocks.AMBER_ORE)
                .add(JurassicBlocks.RICH_AMBER_ORE)
                .add(JurassicBlocks.DEEPSLATE_AMBER_ORE)
                .add(JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE)

                .add(JurassicBlocks.LOW_SECURITY_FENCE)

                .add(JurassicBlocks.DNA_EXTRACTOR)
                .add(JurassicBlocks.DNA_ENHANCER)
                .add(JurassicBlocks.DNA_HYBRIDIZER)
                .add(JurassicBlocks.CULTIVATOR);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.STEEL_BLOCK)
                .add(ModBlocks.STEEL_STAIRS)
                .add(ModBlocks.STEEL_SLAB)
                .add(ModBlocks.STEEL_DOOR)
                .add(ModBlocks.STEEL_TRAPDOOR)

                .add(JurassicBlocks.AMBER_BLOCK_XS)
                .add(JurassicBlocks.AMBER_BLOCK_S)
                .add(JurassicBlocks.AMBER_BLOCK_M);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(JurassicBlocks.AMBER_BLOCK_L)
                .add(JurassicBlocks.AMBER_BLOCK_XL)

                .add(JurassicBlocks.AMBER_ORE)
                .add(JurassicBlocks.DEEPSLATE_AMBER_ORE)

                .add(JurassicBlocks.LOW_SECURITY_FENCE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(JurassicBlocks.RICH_AMBER_ORE)
                .add(JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(JurassicBlocks.LOW_SECURITY_FENCE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.CALCITE_WALL);
    }
}
