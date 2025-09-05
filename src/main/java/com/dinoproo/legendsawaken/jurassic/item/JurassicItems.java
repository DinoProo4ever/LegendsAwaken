package com.dinoproo.legendsawaken.jurassic.item;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import com.dinoproo.legendsawaken.jurassic.item.custom.DNAItem;
import com.dinoproo.legendsawaken.jurassic.sound.JurassicSounds;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class    JurassicItems {
    public static final Item SYNTETIC_EGG = registerItem("syntetic_egg");

    //Amber
    public static final Item AMBER_XS = registerItem("amber_xs");
    public static final Item AMBER_S = registerItem("amber_s");
    public static final Item AMBER_M = registerItem("amber_m");
    public static final Item AMBER_L = registerItem("amber_l");
    public static final Item AMBER_XL = registerItem("amber_xl");

    //Music Discs
    public static final Item MUSIC_DISC_JP_THEME = registerMusicDisc("music_disc_jurassic_park_theme", JurassicSounds.JP_THEME_KEY);

    //Food
    public static final Item RAPTOR_LEG = registerFoodItem("raptor_leg", JurassicFoodComponents.RAPTOR_LEG);
    public static final Item COOKED_RAPTOR_LEG = registerFoodItem("cooked_raptor_leg", JurassicFoodComponents.COOKED_RAPTOR_LEG);

    //DNA
    public static final Item DNA = Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, "dna"), new DNAItem(new Item.Settings()));
    public static final Item HYBRID_DNA = Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, "hybrid_dna"), new DNAItem(new Item.Settings()));

    //Spawn Eggs
    public static final Item VLC_SPAWN_EGG = registerSpawnEgg("velociraptor_spawn_egg", JurassicEntities.VELOCIRAPTOR, 0xaf9063, 0x806c47);
    public static final Item BRC_SPAWN_EGG = registerSpawnEgg("brachiosaurus_spawn_egg", JurassicEntities.BRACHIOSAURUS, 0x8897365, 0x3b3934);



    private static Item registerItem(String name) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name), new Item(new Item.Settings()));
    }

    private static Item registerMusicDisc(String name, RegistryKey<JukeboxSong> songKey) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name), new Item(new Item.Settings().jukeboxPlayable(songKey).maxCount(1)));
    }

    private static Item registerFoodItem(String name, FoodComponent food) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name), new Item(new Item.Settings().food(food)));
    }

    private static <T extends MobEntity> Item registerSpawnEgg(String name, EntityType<T> entity, int shellColor, int spotsColor) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name), new SpawnEggItem(entity, shellColor, spotsColor, new Item.Settings()));
    }

    public static void registerJurassicItems() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Items for " + LegendsAwaken.MOD_ID);
    }
}
