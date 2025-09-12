package com.dinoproo.legendsawaken.world.biome;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(int weight) {
        super(Identifier.of(LegendsAwaken.MOD_ID, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiome(
                mapper,
                ParameterUtils.Temperature.COOL,
                ParameterUtils.Humidity.WET,
                ParameterUtils.Continentalness.INLAND,
                ParameterUtils.Erosion.EROSION_1,
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                0.0f,
                ModBiomes.REDWOOD_FOREST
        );
    }
}
