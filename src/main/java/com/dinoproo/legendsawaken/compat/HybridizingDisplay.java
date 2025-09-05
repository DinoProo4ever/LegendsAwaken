package com.dinoproo.legendsawaken.compat;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.recipe.HybridizingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class HybridizingDisplay extends BasicDisplay {
    public HybridizingDisplay(RecipeEntry<HybridizingRecipe> recipe) {
        super(
                List.of(
                        EntryIngredient.of(EntryStacks.of(dnaComponentA(recipe.value()))),
                        EntryIngredient.of(EntryStacks.of(dnaComponentB(recipe.value())))
                ),
                List.of(EntryIngredient.of(EntryStacks.of(resultDNAComponent(recipe.value()))))
        );
    }

    private static ItemStack dnaComponentA(HybridizingRecipe recipe) {
        ItemStack stack = new ItemStack(recipe.dnaItemA().getMatchingStacks()[0].getItem());
        stack.set(ModDataComponents.SPECIES, recipe.expectedSpeciesA());
        return stack;
    }

    private static ItemStack dnaComponentB(HybridizingRecipe recipe) {
        ItemStack stack = new ItemStack(recipe.dnaItemB().getMatchingStacks()[0].getItem());
        stack.set(ModDataComponents.SPECIES, recipe.expectedSpeciesB());
        return stack;
    }

    private static ItemStack resultDNAComponent(HybridizingRecipe recipe) {
        ItemStack result = recipe.output().copy();
        result.set(ModDataComponents.SPECIES, recipe.outputSpecies());
        return result;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return HybridizingCategory.HYBRIDIZING;
    }
}
