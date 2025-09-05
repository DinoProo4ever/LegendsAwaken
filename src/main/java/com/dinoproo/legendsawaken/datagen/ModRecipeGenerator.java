package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.item.ModItems;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.STEEL_BLOCK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, JurassicItems.AMBER_XS,
                RecipeCategory.DECORATIONS, JurassicBlocks.AMBER_BLOCK_XS);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, JurassicItems.AMBER_S,
                RecipeCategory.DECORATIONS, JurassicBlocks.AMBER_BLOCK_S);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, JurassicItems.AMBER_M,
                RecipeCategory.DECORATIONS, JurassicBlocks.AMBER_BLOCK_M);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, JurassicItems.AMBER_L,
                RecipeCategory.DECORATIONS, JurassicBlocks.AMBER_BLOCK_L);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, JurassicItems.AMBER_XL,
                RecipeCategory.DECORATIONS, JurassicBlocks.AMBER_BLOCK_XL);

        generateCookingRecipes(exporter, "smelting", RecipeSerializer.SMELTING, SmeltingRecipe::new, 200);
        generateCookingRecipes(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100);
        generateCookingRecipes(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600);
    }

    public static <T extends AbstractCookingRecipe> void generateCookingRecipes(RecipeExporter exporter, String cooker, RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, int cookingTime) {
        offerFoodCookingRecipe(exporter, cooker, serializer, recipeFactory, cookingTime, JurassicItems.RAPTOR_LEG, JurassicItems.COOKED_RAPTOR_LEG, 0.35F);
    }
}
