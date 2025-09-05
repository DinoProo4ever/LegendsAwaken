package com.dinoproo.legendsawaken.jurassic.recipe;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.item.custom.DNAItem;
import com.dinoproo.legendsawaken.jurassic.util.JurassicSpecies;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public record ExtractingRecipe (Ingredient inputItem, ItemStack output) implements Recipe<ExtractingRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(ExtractingRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(ExtractingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack result = output.copy();

        if (result.getItem() instanceof DNAItem) {
            List<JurassicSpecies> speciesPool = new ArrayList<>(JurassicSpecies.values());
            speciesPool.remove(JurassicSpecies.INDOMINUS);

            JurassicSpecies species = speciesPool.get(input.getWorld().random.nextInt(speciesPool.size()));
            int level = input.getWorld().random.nextInt(100) + 1;

            result.set(ModDataComponents.SPECIES, species.id().toString());
            result.set(ModDataComponents.DNA_LEVEL, level);
        }

        return result;
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
        return JurassicRecipes.EXTRACTING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return JurassicRecipes.EXTRACTING_TYPE;
    }

    public static class Serializer implements RecipeSerializer<ExtractingRecipe> {
        public static final MapCodec<ExtractingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(ExtractingRecipe::inputItem),
                    ItemStack.CODEC.fieldOf("result").forGetter(ExtractingRecipe::output)
        ).apply(inst, ExtractingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ExtractingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ExtractingRecipe::inputItem,
                        ItemStack.PACKET_CODEC, ExtractingRecipe::output,
                        ExtractingRecipe::new);

        @Override
        public MapCodec<ExtractingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ExtractingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}