package com.dinoproo.legendsawaken.jurassic.recipe;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record CultivatingRecipe(Ingredient eggItem, Ingredient dnaItem, String expectedSpecies, int minLevel, ItemStack output) implements Recipe<CultivatingRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.eggItem);
        list.add(this.dnaItem);
        return list;
    }

    @Override
    public boolean matches(CultivatingRecipeInput input, World world) {
        if (world.isClient()) return false;

        if (!eggItem.test(input.getStackInSlot(0)) || !dnaItem.test(input.getStackInSlot(1))) return false;

        ItemStack dnaStack = input.getStackInSlot(1);
        String speciesId = dnaStack.getOrDefault(ModDataComponents.SPECIES, "none");
        int level = dnaStack.getOrDefault(ModDataComponents.DNA_LEVEL, 0);

        return speciesId.equals(expectedSpecies) && level >= minLevel;
    }

    @Override
    public ItemStack craft(CultivatingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return JurassicRecipes.CULTIVATING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return JurassicRecipes.CULTIVATING_TYPE;
    }

    public static class Serializer implements RecipeSerializer<CultivatingRecipe> {
        public static final MapCodec<CultivatingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("egg").forGetter(CultivatingRecipe::eggItem),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("dna").forGetter(CultivatingRecipe::dnaItem),
                Codec.STRING.fieldOf("species").forGetter(CultivatingRecipe::expectedSpecies),
                Codec.INT.fieldOf("min_level").forGetter(CultivatingRecipe::minLevel),
                ItemStack.CODEC.fieldOf("result").forGetter(CultivatingRecipe::output)
        ).apply(inst,   CultivatingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CultivatingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, CultivatingRecipe::eggItem,
                        Ingredient.PACKET_CODEC, CultivatingRecipe::dnaItem,
                        PacketCodecs.STRING, CultivatingRecipe::expectedSpecies,
                        PacketCodecs.VAR_INT, CultivatingRecipe::minLevel,
                        ItemStack.PACKET_CODEC, CultivatingRecipe::output,
                        CultivatingRecipe::new);

        @Override
        public MapCodec<CultivatingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CultivatingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}