package com.dinoproo.legendsawaken.tensura.item;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TenSuraItems {
    public static final Item MAGICRYSTAL = registerItem("magicrystal");



    private static Item registerItem(String name) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name),
                new Item(new Item.Settings()));
    }

    public static void registerTenSuraItems() {
        LegendsAwaken.LOGGER.info("Registering TenSura Items for " + LegendsAwaken.MOD_ID);
    }
}
