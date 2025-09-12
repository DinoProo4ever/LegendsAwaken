package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.util.ModTags;
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
                .add(JurassicBlocks.FENCE_GATE)

                .add(JurassicBlocks.DNA_EXTRACTOR)
                .add(JurassicBlocks.DNA_ENHANCER)
                .add(JurassicBlocks.DNA_HYBRIDIZER)
                .add(JurassicBlocks.CULTIVATOR);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.SEQUOIA_LOG)
                .add(ModBlocks.SEQUOIA_WOOD)
                .add(ModBlocks.STRIPPED_SEQUOIA_LOG)
                .add(ModBlocks.STRIPPED_SEQUOIA_WOOD)

                .add(ModBlocks.SEQUOIA_PLANKS)
                .add(ModBlocks.SEQUOIA_STAIRS)
                .add(ModBlocks.SEQUOIA_SLAB)
                .add(ModBlocks.SEQUOIA_FENCE)
                .add(ModBlocks.SEQUOIA_FENCE_GATE)
                .add(ModBlocks.SEQUOIA_DOOR)
                .add(ModBlocks.SEQUOIA_TRAPDOOR)
                .add(ModBlocks.SEQUOIA_PRESSURE_PLATE)
                .add(ModBlocks.SEQUOIA_BUTTON);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.SEQUOIA_LEAVES);

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

                .add(JurassicBlocks.LOW_SECURITY_FENCE)
                .add(JurassicBlocks.FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(JurassicBlocks.RICH_AMBER_ORE)
                .add(JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(JurassicBlocks.LOW_SECURITY_FENCE)

                .add(JurassicBlocks.FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.SEQUOIA_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.SEQUOIA_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.SEQUOIA_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.SEQUOIA_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.SEQUOIA_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.SEQUOIA_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.SEQUOIA_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.SEQUOIA_BUTTON);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.CALCITE_WALL)

                .add(JurassicBlocks.LOW_SECURITY_FENCE)

                .add(JurassicBlocks.FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SEQUOIA_LOG)
                .add(ModBlocks.SEQUOIA_WOOD)
                .add(ModBlocks.STRIPPED_SEQUOIA_LOG)
                .add(ModBlocks.STRIPPED_SEQUOIA_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.SEQUOIA_LOGS)
                .add(ModBlocks.SEQUOIA_LOG)
                .add(ModBlocks.SEQUOIA_WOOD)
                .add(ModBlocks.STRIPPED_SEQUOIA_LOG)
                .add(ModBlocks.STRIPPED_SEQUOIA_WOOD);
    }
}
