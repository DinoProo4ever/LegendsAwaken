package com.dinoproo.legendsawaken.world.tree;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final SaplingGenerator SEQUOIA = new SaplingGenerator(LegendsAwaken.MOD_ID + ":sequoia",
            Optional.of(ModConfiguredFeatures.MEGA_SEQUOIA_TREE),
            Optional.of(ModConfiguredFeatures.SEQUOIA_TREE),
            Optional.empty());
}
