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

public record HybridizingRecipe(Ingredient dnaItemA, String expectedSpeciesA, Ingredient dnaItemB, String expectedSpeciesB, ItemStack output, String outputSpecies) implements Recipe<HybridizingRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.dnaItemA);
        list.add(this.dnaItemB);
        return list;
    }

    @Override
    public boolean matches(HybridizingRecipeInput input, World world) {
        if (world.isClient()) return false;

        ItemStack stackA = input.getStackInSlot(0);
        ItemStack stackB = input.getStackInSlot(1);

        if (!dnaItemA.test(stackA) || !dnaItemB.test(stackB)) return false;

        String speciesA = stackA.getOrDefault(ModDataComponents.SPECIES, "unknown");
        String speciesB = stackB.getOrDefault(ModDataComponents.SPECIES, "unknown");

        return (speciesA.equals(expectedSpeciesA) && speciesB.equals(expectedSpeciesB)) ||
                (speciesA.equals(expectedSpeciesB) && speciesB.equals(expectedSpeciesA));
    }

    @Override
    public ItemStack craft(HybridizingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack stackA = input.getStackInSlot(0);
        ItemStack stackB = input.getStackInSlot(1);

        int levelA = stackA.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
        int levelB = stackB.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
        int averageLevel = Math.floorDiv(levelA + levelB, 2);

        ItemStack result = new ItemStack(output.getItem());
        result.set(ModDataComponents.SPECIES, outputSpecies);
        result.set(ModDataComponents.DNA_LEVEL, averageLevel);

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
        return JurassicRecipes.HYBRIDIZING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return JurassicRecipes.HYBRIDIZING_TYPE;
    }

    public static class Serializer implements RecipeSerializer<HybridizingRecipe> {
        public static final MapCodec<HybridizingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("dna_a").forGetter(HybridizingRecipe::dnaItemA),
                Codec.STRING.fieldOf("species_a").forGetter(HybridizingRecipe::expectedSpeciesA),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("dna_b").forGetter(HybridizingRecipe::dnaItemB),
                Codec.STRING.fieldOf("species_b").forGetter(HybridizingRecipe::expectedSpeciesB),
                ItemStack.CODEC.fieldOf("result").forGetter(HybridizingRecipe::output),
                Codec.STRING.fieldOf("result_species").forGetter(HybridizingRecipe::outputSpecies)
        ).apply(inst,   HybridizingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, HybridizingRecipe> STREAM_CODEC =
                PacketCodec.ofStatic(
                        (buf, recipe) -> {
                            Ingredient.PACKET_CODEC.encode(buf, recipe.dnaItemA());
                            PacketCodecs.STRING.encode(buf, recipe.expectedSpeciesA());

                            Ingredient.PACKET_CODEC.encode(buf, recipe.dnaItemB());
                            PacketCodecs.STRING.encode(buf, recipe.expectedSpeciesB());

                            ItemStack.PACKET_CODEC.encode(buf, recipe.output());
                            PacketCodecs.STRING.encode(buf, recipe.outputSpecies());
                        },
                        buf -> new HybridizingRecipe(
                                Ingredient.PACKET_CODEC.decode(buf),
                                PacketCodecs.STRING.decode(buf),

                                Ingredient.PACKET_CODEC.decode(buf),
                                PacketCodecs.STRING.decode(buf),

                                ItemStack.PACKET_CODEC.decode(buf),
                                PacketCodecs.STRING.decode(buf)
                        )
                );

        @Override
        public MapCodec<HybridizingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, HybridizingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}