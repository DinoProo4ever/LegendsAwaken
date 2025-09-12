package com.dinoproo.legendsawaken.block;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.custom.GiantFernBlock;
import com.dinoproo.legendsawaken.block.item.custom.GiantFernBlockItem;
import com.dinoproo.legendsawaken.world.tree.ModSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //Sequoia
    public static final Block SEQUOIA_LOG = registerBlock("sequoia_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block SEQUOIA_WOOD = registerBlock("sequoia_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_SEQUOIA_LOG = registerBlock("stripped_sequoia_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_SEQUOIA_WOOD = registerBlock("stripped_sequoia_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block SEQUOIA_PLANKS = registerBlock("sequoia_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SEQUOIA_STAIRS = registerBlock("sequoia_stairs",
            new StairsBlock(SEQUOIA_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block SEQUOIA_SLAB = registerBlock("sequoia_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    public static final Block SEQUOIA_FENCE = registerBlock("sequoia_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block SEQUOIA_FENCE_GATE = registerBlock("sequoia_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    public static final Block SEQUOIA_DOOR = registerBlock("sequoia_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block SEQUOIA_TRAPDOOR = registerBlock("sequoia_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    public static final Block SEQUOIA_PRESSURE_PLATE = registerBlock("sequoia_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block SEQUOIA_BUTTON = registerBlock("sequoia_button",
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));

    public static final Block SEQUOIA_LEAVES = registerBlock("sequoia_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block SEQUOIA_SAPLING = registerBlock("sequoia_sapling",
            new SaplingBlock(ModSaplingGenerator.SEQUOIA, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block REINFORCED_GLASS = registerTransparentBlock("reinforced_glass", 1.2f, BlockSoundGroup.GLASS);

    //Calcite
    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            new StairsBlock(Blocks.CALCITE.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.CALCITE)));
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
    public static final Block CALCITE_WALL = registerBlock("calcite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

    //Steel
    public static final Block STEEL_BLOCK = registerHardBlock("steel_block", 4f, BlockSoundGroup.METAL);
    public static final Block STEEL_STAIRS = registerBlock("steel_stairs",
            new StairsBlock(STEEL_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(STEEL_BLOCK)));
    public static final Block STEEL_SLAB = registerBlock("steel_slab",
            new SlabBlock(AbstractBlock.Settings.copy(STEEL_BLOCK)));
    public static final Block STEEL_DOOR = registerBlock("steel_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(STEEL_BLOCK)));
    public static final Block STEEL_TRAPDOOR = registerBlock("steel_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(STEEL_BLOCK)));

    public static final Block GIANT_FERN = Registry.register(
            Registries.BLOCK, Identifier.of(LegendsAwaken.MOD_ID, "giant_fern"),
            new GiantFernBlock(AbstractBlock.Settings.copy(Blocks.LARGE_FERN))
    );
    public static final Item GIANT_FERN_ITEM = Registry.register(
            Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, "giant_fern"),
            new GiantFernBlockItem(GIANT_FERN, new Item.Settings())
    );


    private static Block registerHardBlock(String name, float strength, BlockSoundGroup sound) {
        return registerBlock(name, new Block(AbstractBlock.Settings.create().strength(strength).requiresTool().sounds(sound)));
    }

    private static Block registerTransparentBlock(String name, float strength, BlockSoundGroup sound) {
        return registerBlock(name, new TransparentBlock(AbstractBlock.Settings.create().nonOpaque().strength(strength).requiresTool().sounds(sound)));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LegendsAwaken.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        LegendsAwaken.LOGGER.info("Registering Core Blocks for " + LegendsAwaken.MOD_ID);
    }
}
