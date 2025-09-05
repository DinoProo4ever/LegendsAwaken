package com.dinoproo.legendsawaken.block;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block REINFORCED_GLASS = registerTransparentBlock("reinforced_glass", 1.2f, BlockSoundGroup.GLASS);

    //Calcite
    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            new StairsBlock(Blocks.CALCITE.getDefaultState(),
                    AbstractBlock.Settings.create().nonOpaque().strength(0.75f).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(0.75f).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_WALL = registerBlock("calcite_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(0.75f).requiresTool().sounds(BlockSoundGroup.CALCITE)));

    //Steel
    public static final Block STEEL_BLOCK = registerHardBlock("steel_block", 4f, BlockSoundGroup.METAL);
    public static final Block STEEL_STAIRS = registerBlock("steel_stairs",
            new StairsBlock(ModBlocks.STEEL_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().nonOpaque().strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block STEEL_SLAB = registerBlock("steel_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block STEEL_DOOR = registerBlock("steel_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().nonOpaque().strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block STEEL_TRAPDOOR = registerBlock("steel_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().nonOpaque().strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)));

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
