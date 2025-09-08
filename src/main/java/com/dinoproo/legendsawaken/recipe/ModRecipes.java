package com.dinoproo.legendsawaken.recipe;

import com.dinoproo.legendsawaken.LegendsAwaken;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vazkii.patchouli.api.PatchouliAPI;

public class ModRecipes {
    //Patchouli
    public static final RecipeSerializer<ShapelessBookRecipe> SHAPELESS_BOOK_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(PatchouliAPI.MOD_ID, "shapeless_book_recipe"),
            new ShapelessBookRecipe.Serializer());

    public static void registerRecipes() {
        LegendsAwaken.LOGGER.info("Registering Core Custom Recipes for " + LegendsAwaken.MOD_ID);
    }
}
