package com.dinoproo.legendsawaken.world;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> AMBER_ORE_PLACED = registerKey("amber_ore_placed");
    public static final RegistryKey<PlacedFeature> RICH_AMBER_ORE_PLACED = registerKey("rich_amber_ore_placed");

    public static final RegistryKey<PlacedFeature> SEQUOIA_TREE_PLACED = registerKey("sequoia_placed");
    public static final RegistryKey<PlacedFeature> MEGA_SEQUOIA_TREE_PLACED = registerKey("mega_sequoia_placed");

    public static final RegistryKey<PlacedFeature> GIANT_FERN_PLACED = registerKey("giant_fern_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, AMBER_ORE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.AMBER_ORE),
                ModOrePlacement.modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, RICH_AMBER_ORE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.RICH_AMBER_ORE),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, SEQUOIA_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEQUOIA_TREE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(3, 0.1f, 2),
                        ModBlocks.SEQUOIA_SAPLING
                ));
        register(context, MEGA_SEQUOIA_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.MEGA_SEQUOIA_TREE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(3, 0.1f, 2),
                        ModBlocks.SEQUOIA_SAPLING
                ));

        register(context, GIANT_FERN_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.GIANT_FERN),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 1),
                        ModBlocks.GIANT_FERN
                ));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(LegendsAwaken.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
