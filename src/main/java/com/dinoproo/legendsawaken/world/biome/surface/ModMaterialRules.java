package com.dinoproo.legendsawaken.world.biome.surface;

import com.dinoproo.legendsawaken.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = block(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = block(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule PODZOL = block(Blocks.PODZOL);
    private static final MaterialRules.MaterialRule COARSE_DIRT = block(Blocks.COARSE_DIRT);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT
        );
        MaterialRules.MaterialRule redwoodSurface = MaterialRules.sequence(
                MaterialRules.condition(
                        isAtOrAboveWaterLevel, MaterialRules.sequence(
                                MaterialRules.condition(surfaceNoiseThreshold(1.75), COARSE_DIRT),
                                MaterialRules.condition(surfaceNoiseThreshold(-0.95), PODZOL),
                                GRASS_BLOCK
                        )
                ), DIRT
        );

        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.REDWOOD_FOREST),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, redwoodSurface)
                ),

                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule block(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

    private static MaterialRules.MaterialCondition surfaceNoiseThreshold(double min) {
        return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE);
    }
}
