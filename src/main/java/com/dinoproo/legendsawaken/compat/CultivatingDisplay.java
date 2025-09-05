package com.dinoproo.legendsawaken.compat;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.recipe.CultivatingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class CultivatingDisplay extends BasicDisplay {
    public CultivatingDisplay(RecipeEntry<CultivatingRecipe> recipe) {
        super(List.of(
                    EntryIngredient.of(EntryStacks.of(recipe.value().eggItem().getMatchingStacks()[0])),
                    EntryIngredient.of(EntryStacks.of(dnaComponents(recipe.value())))
                ),
                List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    private static ItemStack dnaComponents (CultivatingRecipe recipe) {
        ItemStack stack = new ItemStack(recipe.dnaItem().getMatchingStacks()[0].getItem());
        stack.set(ModDataComponents.SPECIES, recipe.expectedSpecies());
        stack.set(ModDataComponents.DNA_LEVEL, recipe.minLevel());
        return stack;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CultivatingCategory.CULTIVATING;
    }
}
