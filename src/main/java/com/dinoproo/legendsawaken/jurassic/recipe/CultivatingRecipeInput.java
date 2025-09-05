package com.dinoproo.legendsawaken.jurassic.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record CultivatingRecipeInput(ItemStack eggInput, ItemStack dnaInput) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> eggInput;
            case 1 -> dnaInput;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 2;
    }
}
