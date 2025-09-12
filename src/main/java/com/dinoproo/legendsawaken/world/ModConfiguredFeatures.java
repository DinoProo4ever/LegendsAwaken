    package com.dinoproo.legendsawaken.world;

    import com.dinoproo.legendsawaken.LegendsAwaken;
    import com.dinoproo.legendsawaken.block.ModBlocks;
    import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
    import com.dinoproo.legendsawaken.world.tree.MegaSequoiaFoliagePlacer;
    import net.minecraft.registry.Registerable;
    import net.minecraft.registry.RegistryKey;
    import net.minecraft.registry.RegistryKeys;
    import net.minecraft.registry.tag.BlockTags;
    import net.minecraft.structure.rule.RuleTest;
    import net.minecraft.structure.rule.TagMatchRuleTest;
    import net.minecraft.util.Identifier;
    import net.minecraft.util.math.intprovider.ConstantIntProvider;
    import net.minecraft.util.math.intprovider.UniformIntProvider;
    import net.minecraft.world.gen.feature.*;
    import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
    import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
    import net.minecraft.world.gen.stateprovider.BlockStateProvider;
    import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
    import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

    import java.util.List;

    public class ModConfiguredFeatures {
        public static final RegistryKey<ConfiguredFeature<?, ?>> AMBER_ORE = registerKey("amber_ore");
        public static final RegistryKey<ConfiguredFeature<?, ?>> RICH_AMBER_ORE = registerKey("rich_amber_ore");

        public static final RegistryKey<ConfiguredFeature<?, ?>> SEQUOIA_TREE = registerKey("sequoia_tree");
        public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_SEQUOIA_TREE = registerKey("mega_sequoia_tree");

        public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_FERN = registerKey("giant_fern");

        public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
            RuleTest stoneReplaceable = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
            RuleTest deepslateReplaceable = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

            List<OreFeatureConfig.Target> overworldAmberOres =
                    List.of(OreFeatureConfig.createTarget(stoneReplaceable, JurassicBlocks.AMBER_ORE.getDefaultState()),
                            OreFeatureConfig.createTarget(deepslateReplaceable, JurassicBlocks.DEEPSLATE_AMBER_ORE.getDefaultState()));
            List<OreFeatureConfig.Target> overworldRichAmberOres =
                    List.of(OreFeatureConfig.createTarget(stoneReplaceable, JurassicBlocks.RICH_AMBER_ORE.getDefaultState()),
                            OreFeatureConfig.createTarget(deepslateReplaceable, JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE.getDefaultState()));

            TreeFeatureConfig sequoiaConfig = new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SEQUOIA_LOG),
                    new StraightTrunkPlacer(13, 4, 0),

                    BlockStateProvider.of(ModBlocks.SEQUOIA_LEAVES),
                    new SpruceFoliagePlacer(
                            UniformIntProvider.create(2, 3),
                            UniformIntProvider.create(0, 2),
                            UniformIntProvider.create(1, 2)
                    ),

                    new TwoLayersFeatureSize(2, 2, 2)
            ).build();
            TreeFeatureConfig megaSequoiaConfig = new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SEQUOIA_LOG),
                    new GiantTrunkPlacer(26, 4, 24),

                    BlockStateProvider.of(ModBlocks.SEQUOIA_LEAVES),
                    new MegaSequoiaFoliagePlacer(
                            ConstantIntProvider.create(0),
                            ConstantIntProvider.create(0),
                            UniformIntProvider.create(8, 20)
                    ),

                    new TwoLayersFeatureSize(2, 2, 2)
            ).build();

            RandomPatchFeatureConfig gianFernConfig = new RandomPatchFeatureConfig(
                    32, 7, 3,
                    PlacedFeatures.createEntry(
                            Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.GIANT_FERN))
                    )
            );

            register(context, AMBER_ORE, Feature.ORE, new OreFeatureConfig(overworldAmberOres, 8));
            register(context, RICH_AMBER_ORE, Feature.ORE, new OreFeatureConfig(overworldRichAmberOres, 4));

            register(context, SEQUOIA_TREE, Feature.TREE, sequoiaConfig);
            register(context, MEGA_SEQUOIA_TREE, Feature.TREE, megaSequoiaConfig);

            register(context, GIANT_FERN, Feature.RANDOM_PATCH, gianFernConfig);
        }

        public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
            return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(LegendsAwaken.MOD_ID, name));
        }

        private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }
    }
