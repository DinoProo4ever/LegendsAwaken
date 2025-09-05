package com.dinoproo.legendsawaken.component;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModDataComponents {
    public static final ComponentType<String> SPECIES = ComponentType.<String>builder().codec(Codec.STRING).build();
    public static final ComponentType<Integer> DNA_LEVEL = ComponentType.<Integer>builder().codec(Codec.INT).build();

    public static void registerDataComponents() {
        LegendsAwaken.LOGGER.info("Registering Data Components for " + LegendsAwaken.MOD_ID);

        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(LegendsAwaken.MOD_ID, "species"), SPECIES);
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(LegendsAwaken.MOD_ID, "dna_level"), DNA_LEVEL);
    }
}
