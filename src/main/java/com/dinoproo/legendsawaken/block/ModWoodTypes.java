package com.dinoproo.legendsawaken.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModWoodTypes {
    public static WoodType SEQUOIA;

    public static void register() {

    }

    public static WoodType createSequoia() {
        return new WoodType(
                "sequoia",
                BlockSetType.OAK,
                BlockSoundGroup.WOOD,
                BlockSoundGroup.HANGING_SIGN,
                SoundEvents.BLOCK_FENCE_GATE_CLOSE,
                SoundEvents.BLOCK_FENCE_GATE_OPEN
        );
    }
}
