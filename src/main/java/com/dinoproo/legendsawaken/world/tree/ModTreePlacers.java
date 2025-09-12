package com.dinoproo.legendsawaken.world.tree;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModTreePlacers {
    public static FoliagePlacerType<MegaSequoiaFoliagePlacer> MEGA_SEQUOIA_FOLIAGE_PLACER;

    public static void registerTreePlacers() {
        MEGA_SEQUOIA_FOLIAGE_PLACER = Registry.register(Registries.FOLIAGE_PLACER_TYPE,
                Identifier.of(LegendsAwaken.MOD_ID, "mega_sequoia_foliage_placer"),
                new FoliagePlacerType<>(MegaSequoiaFoliagePlacer.CODEC));
    }
}
