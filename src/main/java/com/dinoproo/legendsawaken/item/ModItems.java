package com.dinoproo.legendsawaken.item;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.block.item.custom.GiantFernBlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CHITIN = registerItem("chitin");
    public static final Item KERATIN = registerItem("keratin");
    public static final Item SILICA_PEARLS = registerItem("silica_pearls");
    public static final Item BLACK_PEARL = registerItem("black_pearl");

    public static final Item STEEL_INGOT = registerItem("steel_ingot");
    public static final Item CEMENTING_PASTE = registerItem("cementing_paste");
    public static final Item ELECTRONICS = registerItem("electronics");

    public static final Item HOLO_EMITTER = registerItem("holo_emitter");

    public static final Item POLYMER = registerItem("polymer");
    public static final Item ORGANIC_POLYMER = registerItem("organic_polymer");

    public static final Item ELEMENT = registerItem("element");
    public static final Item ELEMENT_SHARD = registerItem("element_shard");
    public static final Item ELEMENT_DUST = registerItem("element_dust");

    public static final Item WRENCH = registerCustomItem("wrench", new Item(new Item.Settings().maxCount(1)));

    public static final Item SYRINGE = registerItem("syringe");
    public static final Item GROWTH_INHIBITOR = registerItem("growth_inhibitor");
    public static final Item GROWTH_SERUM = registerItem("growth_serum");


    public static final Item SEQUOIA_SIGN = registerCustomItem("sequoia_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.SEQUOIA_SIGN, ModBlocks.SEQUOIA_WALL_SIGN));
    public static final Item SEQUOIA_HANGING_SIGN = registerCustomItem("sequoia_hanging_sign",
            new HangingSignItem(ModBlocks.SEQUOIA_HANGING_SIGN, ModBlocks.SEQUOIA_WALL_HANGING_SIGN,
                    new Item.Settings().maxCount(16)));

    public static final Item GIANT_FERN = Registry.register(
            Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, "giant_fern"),
            new GiantFernBlockItem(ModBlocks.GIANT_FERN, new Item.Settings())
    );



    private static Item registerCustomItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name), item);
    }

    private static Item registerItem(String name) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name),
                new Item(new Item.Settings()));
    }

    public static void registerItems() {
        LegendsAwaken.LOGGER.info("Registering Core Items for " + LegendsAwaken.MOD_ID);
    }
}
