package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.item.ModItems;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import com.dinoproo.legendsawaken.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends FabricTagProvider.ItemTagProvider {
    public ModItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SEQUOIA_LOG.asItem())
                .add(ModBlocks.SEQUOIA_WOOD.asItem())
                .add(ModBlocks.STRIPPED_SEQUOIA_LOG.asItem())
                .add(ModBlocks.STRIPPED_SEQUOIA_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.SEQUOIA_PLANKS.asItem());

        getOrCreateTagBuilder(ModTags.Items.KERATIN)
                .add(ModItems.CHITIN)
                .add(ModItems.KERATIN)
                .add(Items.ARMADILLO_SCUTE)
                .add(Items.TURTLE_SCUTE);

        getOrCreateTagBuilder(ModTags.Items.POLYMER)
                .add(ModItems.POLYMER)
                .add(ModItems.ORGANIC_POLYMER);

        getOrCreateTagBuilder(ModTags.Items.SEQUOIA_LOGS)
                .add(ModBlocks.SEQUOIA_LOG.asItem())
                .add(ModBlocks.SEQUOIA_WOOD.asItem())
                .add(ModBlocks.STRIPPED_SEQUOIA_LOG.asItem())
                .add(ModBlocks.STRIPPED_SEQUOIA_WOOD.asItem());

        getOrCreateTagBuilder(ModTags.Items.AMBER)
                .add(JurassicItems.AMBER_XS)
                .add(JurassicItems.AMBER_S)
                .add(JurassicItems.AMBER_M)
                .add(JurassicItems.AMBER_L)
                .add(JurassicItems.AMBER_XL);

        getOrCreateTagBuilder(ModTags.Items.DNA)
                .add(JurassicItems.DNA)
                .add(JurassicItems.HYBRID_DNA);
    }
}
