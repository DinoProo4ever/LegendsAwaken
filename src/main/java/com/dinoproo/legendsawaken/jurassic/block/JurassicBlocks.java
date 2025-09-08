package com.dinoproo.legendsawaken.jurassic.block;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class JurassicBlocks {
    //Amber
    public static final Block AMBER_BLOCK_XS = registerHardBlock("amber_block_xs", 2f, BlockSoundGroup.METAL);
    public static final Block AMBER_BLOCK_S = registerHardBlock("amber_block_s", 2.5f, BlockSoundGroup.METAL);
    public static final Block AMBER_BLOCK_M = registerHardBlock("amber_block_m", 3f, BlockSoundGroup.METAL);
    public static final Block AMBER_BLOCK_L = registerHardBlock("amber_block_l", 3.5f, BlockSoundGroup.METAL);
    public static final Block AMBER_BLOCK_XL = registerHardBlock("amber_block_xl", 4f, BlockSoundGroup.METAL);

    public static final Block AMBER_ORE = registerOreBlock("amber_ore", 2, 5,
            3.5f, BlockSoundGroup.STONE);
    public static final Block RICH_AMBER_ORE = registerOreBlock("rich_amber_ore", 3, 7,
            4.5f, BlockSoundGroup.STONE);
    public static final Block DEEPSLATE_AMBER_ORE = registerOreBlock("deepslate_amber_ore", 2, 5,
            4f, BlockSoundGroup.DEEPSLATE);
    public static final Block DEEPSLATE_RICH_AMBER_ORE = registerOreBlock("deepslate_rich_amber_ore", 3,
            7, 5f, BlockSoundGroup.DEEPSLATE);

    //Fences
    public static final Block LOW_SECURITY_FENCE = registerBlock("low_security_fence",
            new ElectrifiedFenceBlock(AbstractBlock.Settings.create()
                    .strength(3F).nonOpaque().requiresTool().sounds(BlockSoundGroup.METAL), 0.5F));

    public static final Block FENCE_GATE = registerBlock("fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.create().strength(12F).nonOpaque().requiresTool().sounds(BlockSoundGroup.METAL)));

    public static final Block TEN_K_VOLTS_SIGN = registerBlock("10k_volts_sign",
            new FenceSignBlock(AbstractBlock.Settings.create().noCollision().sounds(BlockSoundGroup.STONE)));

    //Eggs
    public static final Block VLC_EGG = registerBlock("velociraptor_egg",
            new VLCEggBlock(AbstractBlock.Settings.create()
            .nonOpaque().strength(1f).ticksRandomly().sounds(BlockSoundGroup.STONE)));

    //Functional
    public static final Block DNA_EXTRACTOR = registerBlock("dna_extractor",
            new DNAExtractorBlock(AbstractBlock.Settings.create()
                    .nonOpaque().strength(3f).requiresTool().sounds(BlockSoundGroup.METAL).luminance(value -> 1)));
    public static final Block DNA_ENHANCER = registerBlock("dna_enhancer",
            new DNAEnhancerBlock(AbstractBlock.Settings.create()
                    .nonOpaque().strength(3f).requiresTool().sounds(BlockSoundGroup.METAL).luminance(value -> 1)));
    public static final Block DNA_HYBRIDIZER = registerBlock("dna_hybridizer",
            new DNAHybridizerBlock(AbstractBlock.Settings.create()
                    .nonOpaque().strength(3f).requiresTool().sounds(BlockSoundGroup.METAL).luminance(value -> 1)));
    public static final Block CULTIVATOR = registerBlock("cultivator",
            new CultivatorBlock(AbstractBlock.Settings.create()
                    .nonOpaque().strength(3f).requiresTool().sounds(BlockSoundGroup.GLASS).luminance(value -> 1)));


    private static Block registerHardBlock(String name, float strength, BlockSoundGroup sound) {
        return registerBlock(name, new Block(AbstractBlock.Settings.create()
                .strength(strength).requiresTool().sounds(sound)));
    }

    private static Block registerOreBlock(String name, int minXP, int maxXP, float strength, BlockSoundGroup sound) {
        return registerBlock(name, new ExperienceDroppingBlock(UniformIntProvider.create(minXP, maxXP),
                AbstractBlock.Settings.create().strength(strength).requiresTool().sounds(sound).nonOpaque()));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LegendsAwaken.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerJurassicBlocks() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Blocks for " + LegendsAwaken.MOD_ID);
    }
}
