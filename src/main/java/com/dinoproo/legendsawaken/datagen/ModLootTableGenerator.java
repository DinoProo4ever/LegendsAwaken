package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    protected static final float[] SAPLING_DROP_CHANCE = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};

    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.REINFORCED_GLASS);

        addDrop(ModBlocks.SEQUOIA_LOG);
        addDrop(ModBlocks.SEQUOIA_WOOD);
        addDrop(ModBlocks.STRIPPED_SEQUOIA_LOG);
        addDrop(ModBlocks.STRIPPED_SEQUOIA_WOOD);

        addDrop(ModBlocks.SEQUOIA_PLANKS);
        addDrop(ModBlocks.SEQUOIA_STAIRS);
        addDrop(ModBlocks.SEQUOIA_SLAB, slabDrops(ModBlocks.SEQUOIA_SLAB));
        addDrop(ModBlocks.SEQUOIA_FENCE);
        addDrop(ModBlocks.SEQUOIA_FENCE_GATE);
        addDrop(ModBlocks.SEQUOIA_DOOR, doorDrops(ModBlocks.SEQUOIA_DOOR));
        addDrop(ModBlocks.SEQUOIA_TRAPDOOR);
        addDrop(ModBlocks.SEQUOIA_PRESSURE_PLATE);
        addDrop(ModBlocks.SEQUOIA_BUTTON);

        addDrop(ModBlocks.SEQUOIA_LEAVES, leavesDrops(ModBlocks.SEQUOIA_LEAVES, ModBlocks.SEQUOIA_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.SEQUOIA_SAPLING);

        addDrop(ModBlocks.CALCITE_STAIRS);
        addDrop(ModBlocks.CALCITE_SLAB);
        addDrop(ModBlocks.CALCITE_WALL);

        addDrop(ModBlocks.STEEL_BLOCK);
        addDrop(ModBlocks.STEEL_STAIRS);
        addDrop(ModBlocks.STEEL_SLAB, slabDrops(ModBlocks.STEEL_SLAB));
        addDrop(ModBlocks.STEEL_DOOR, doorDrops(ModBlocks.STEEL_DOOR));
        addDrop(ModBlocks.STEEL_TRAPDOOR);

        addDrop(JurassicBlocks.AMBER_BLOCK_XS);
        addDrop(JurassicBlocks.AMBER_BLOCK_S);
        addDrop(JurassicBlocks.AMBER_BLOCK_M);
        addDrop(JurassicBlocks.AMBER_BLOCK_L);
        addDrop(JurassicBlocks.AMBER_BLOCK_XL);

        addDrop(JurassicBlocks.LOW_SECURITY_FENCE);

        addDrop(JurassicBlocks.FENCE_GATE);

        addDrop(JurassicBlocks.VLC_EGG);

        addDrop(JurassicBlocks.DNA_EXTRACTOR);
        addDrop(JurassicBlocks.DNA_ENHANCER);
        addDrop(JurassicBlocks.DNA_HYBRIDIZER);
        addDrop(JurassicBlocks.CULTIVATOR);
    }
}
