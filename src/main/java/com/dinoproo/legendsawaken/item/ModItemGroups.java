package com.dinoproo.legendsawaken.item;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import com.dinoproo.legendsawaken.tensura.item.TenSuraItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BUILDING_BLOCKS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "building_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SEQUOIA_PLANKS))
                    .displayName(Text.translatable("itemgroup.legendsawaken.building_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SEQUOIA_LOG);
                        entries.add(ModBlocks.SEQUOIA_WOOD);
                        entries.add(ModBlocks.STRIPPED_SEQUOIA_LOG);
                        entries.add(ModBlocks.STRIPPED_SEQUOIA_WOOD);

                        entries.add(ModBlocks.SEQUOIA_PLANKS);
                        entries.add(ModBlocks.SEQUOIA_STAIRS);
                        entries.add(ModBlocks.SEQUOIA_SLAB);
                        entries.add(ModBlocks.SEQUOIA_FENCE);
                        entries.add(ModBlocks.SEQUOIA_FENCE_GATE);

                        entries.add(ModBlocks.REINFORCED_GLASS);

                        entries.add(ModBlocks.CALCITE_STAIRS);
                        entries.add(ModBlocks.CALCITE_SLAB);
                        entries.add(ModBlocks.CALCITE_WALL);

                        entries.add(ModBlocks.STEEL_BLOCK);
                        entries.add(ModBlocks.STEEL_STAIRS);
                        entries.add(ModBlocks.STEEL_SLAB);
                        entries.add(ModBlocks.STEEL_DOOR);
                        entries.add(ModBlocks.STEEL_TRAPDOOR);

                        entries.add(JurassicBlocks.AMBER_BLOCK_XS);
                        entries.add(JurassicBlocks.AMBER_BLOCK_S);
                        entries.add(JurassicBlocks.AMBER_BLOCK_M);
                        entries.add(JurassicBlocks.AMBER_BLOCK_L);
                        entries.add(JurassicBlocks.AMBER_BLOCK_XL);

                        entries.add(JurassicBlocks.LOW_SECURITY_FENCE);
                        entries.add(JurassicBlocks.FENCE_GATE);
                    }).build());

    public static final ItemGroup NATURAL_BLOCKS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "natural_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SEQUOIA_LEAVES))
                    .displayName(Text.translatable("itemgroup.legendsawaken.natural_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SEQUOIA_LEAVES);
                        entries.add(ModBlocks.SEQUOIA_SAPLING);

                        entries.add(JurassicBlocks.AMBER_ORE);
                        entries.add(JurassicBlocks.RICH_AMBER_ORE);
                        entries.add(JurassicBlocks.DEEPSLATE_AMBER_ORE);
                        entries.add(JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE);
                    }).build());

    public static final ItemGroup FUNCTIONAL_BLOCKS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "functional_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(JurassicBlocks.DNA_EXTRACTOR))
                    .displayName(Text.translatable("itemgroup.legendsawaken.functional_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(JurassicBlocks.DNA_EXTRACTOR);
                        entries.add(JurassicBlocks.DNA_ENHANCER);
                        entries.add(JurassicBlocks.DNA_HYBRIDIZER);
                        entries.add(JurassicBlocks.CULTIVATOR);
                    }).build());

    public static final ItemGroup REDSTONE_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "redstone"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SEQUOIA_PRESSURE_PLATE))
                    .displayName(Text.translatable("itemgroup.legendsawaken.redstone"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SEQUOIA_DOOR);
                        entries.add(ModBlocks.SEQUOIA_TRAPDOOR);
                        entries.add(ModBlocks.SEQUOIA_PRESSURE_PLATE);
                        entries.add(ModBlocks.SEQUOIA_BUTTON);
                    }).build());

    public static final ItemGroup TOOLS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "tools"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GROWTH_INHIBITOR))
                    .displayName(Text.translatable("itemgroup.legendsawaken.tools"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WRENCH);

                        entries.add(ModItems.SYRINGE);
                        entries.add(ModItems.GROWTH_INHIBITOR);
                        entries.add(ModItems.GROWTH_SERUM);
                    }).build());

    public static final ItemGroup INGREDIENTS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "ingredients"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ELEMENT))
                    .displayName(Text.translatable("itemgroup.legendsawaken.ingredients"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CHITIN);
                        entries.add(ModItems.KERATIN);
                        entries.add(ModItems.SILICA_PEARLS);
                        entries.add(ModItems.BLACK_PEARL);

                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.CEMENTING_PASTE);
                        entries.add(ModItems.ELECTRONICS);

                        entries.add(ModItems.HOLO_EMITTER);

                        entries.add(ModItems.POLYMER);
                        entries.add(ModItems.ORGANIC_POLYMER);

                        entries.add(ModItems.ELEMENT);
                        entries.add(ModItems.ELEMENT_SHARD);
                        entries.add(ModItems.ELEMENT_DUST);

                        entries.add(JurassicItems.AMBER_XS);
                        entries.add(JurassicItems.AMBER_S);
                        entries.add(JurassicItems.AMBER_M);
                        entries.add(JurassicItems.AMBER_L);
                        entries.add(JurassicItems.AMBER_XL);

                        entries.add(JurassicItems.DNA);
                        entries.add(JurassicItems.HYBRID_DNA);

                        entries.add(JurassicItems.SYNTETIC_EGG);

                        entries.add(JurassicItems.RAPTOR_LEG);
                        entries.add(JurassicItems.COOKED_RAPTOR_LEG);

                       entries.add(TenSuraItems.MAGICRYSTAL);
                    }).build());

    public static final ItemGroup MISC_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "misc"),
            FabricItemGroup.builder().icon(() -> new ItemStack(JurassicItems.MUSIC_DISC_JP_THEME))
                    .displayName(Text.translatable("itemgroup.legendsawaken.misc"))
                    .entries((displayContext, entries) -> entries.add(JurassicItems.MUSIC_DISC_JP_THEME)).build());

    public static final ItemGroup SPAWN_EGGS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendsAwaken.MOD_ID, "spawn_eggs"),
            FabricItemGroup.builder().icon(() -> new ItemStack(JurassicItems.VLC_SPAWN_EGG))
                    .displayName(Text.translatable("itemgroup.legendsawaken.spawn_eggs"))
                    .entries((displayContext, entries) -> {
                        entries.add(JurassicItems.VLC_SPAWN_EGG);
                        entries.add(JurassicItems.BRC_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups() {
        LegendsAwaken.LOGGER.info("Registering Item Groups for " + LegendsAwaken.MOD_ID);
    }
}



