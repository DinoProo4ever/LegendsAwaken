package com.dinoproo.legendsawaken.jurassic.sound;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class JurassicSounds {
    public static final SoundEvent JP_THEME = registerSoundEvent("jurassic_park_theme");
    public static final RegistryKey<JukeboxSong> JP_THEME_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(LegendsAwaken.MOD_ID, "jurassic_park_theme"));

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(LegendsAwaken.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerJurassicSounds() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Sounds for " + LegendsAwaken.MOD_ID);
    }
}
