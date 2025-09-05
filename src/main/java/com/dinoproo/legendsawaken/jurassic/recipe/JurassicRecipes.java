package com.dinoproo.legendsawaken.jurassic.recipe;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JurassicRecipes {
    public static final RecipeSerializer<ExtractingRecipe> EXTRACTING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LegendsAwaken.MOD_ID, "extracting"),
            new ExtractingRecipe.Serializer());
    public static final RecipeType<ExtractingRecipe> EXTRACTING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LegendsAwaken.MOD_ID, "extracting"), new RecipeType<ExtractingRecipe>() {
                @Override
                public String toString() {
                    return "extracting";
                }
            });

    public static final RecipeSerializer<HybridizingRecipe> HYBRIDIZING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LegendsAwaken.MOD_ID, "hybridizing"),
            new HybridizingRecipe.Serializer());
    public static final RecipeType<HybridizingRecipe> HYBRIDIZING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LegendsAwaken.MOD_ID, "hybridizing"), new RecipeType<HybridizingRecipe>() {
                @Override
                public String toString() {
                    return "extracting";
                }
            });

    public static final RecipeSerializer<CultivatingRecipe> CULTIVATING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LegendsAwaken.MOD_ID, "cultivating"),
            new CultivatingRecipe.Serializer());
    public static final RecipeType<CultivatingRecipe> CULTIVATING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LegendsAwaken.MOD_ID, "cultivating"), new RecipeType<CultivatingRecipe>() {
                @Override
                public String toString() {
                    return "cultivating";
                }
            });

    public static void registerRecipes() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Custom Recipes for " + LegendsAwaken.MOD_ID);
    }
}
