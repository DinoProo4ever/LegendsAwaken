package com.dinoproo.legendsawaken.jurassic.util;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public record JurassicSpecies(Identifier id, Text displayName) {
    private static final Map<Identifier, JurassicSpecies> SPECIES_MAP = new LinkedHashMap<>();

    public static final JurassicSpecies VELOCIRAPTOR = registerSpecie("velociraptor");
    public static final JurassicSpecies BRACHIOSAURUS = registerSpecie("brachiosaurus");
    public static final JurassicSpecies PARASAUROLOPHUS = registerSpecie("parasaurolophus");
    public static final JurassicSpecies TYRANNOSAURUS = registerSpecie("tyrannosaurus");
    public static final JurassicSpecies INDOMINUS = registerSpecie("indominus");

    private static JurassicSpecies registerSpecie(String id) {
        Identifier identifier = Identifier.of(LegendsAwaken.MOD_ID, id);
        JurassicSpecies species = new JurassicSpecies(identifier, Text.translatable("entity.legendsawaken." + id));
        SPECIES_MAP.put(identifier, species);
        return species;
    }

    public static JurassicSpecies get(Identifier id) {
        return SPECIES_MAP.get(id);
    }

    public static Collection<JurassicSpecies> values() {
        return SPECIES_MAP.values();
    }

    public static void registerJurassicSpecies() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Species for " + LegendsAwaken.MOD_ID);
    }
}
