package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.REINFORCED_GLASS);

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
