package com.dinoproo.legendsawaken.util;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SEQUOIA_LOGS = createTag("sequoia_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(LegendsAwaken.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> KERATIN = createTag("keratin");
        public static final TagKey<Item> POLYMER = createTag("polymer");

        public static final TagKey<Item> SEQUOIA_LOGS = createTag("sequoia_logs");

        public static final TagKey<Item> AMBER = createTag("amber");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(LegendsAwaken.MOD_ID, name));
        }
    }
}
