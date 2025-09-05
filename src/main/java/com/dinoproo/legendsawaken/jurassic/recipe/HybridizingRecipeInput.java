package com.dinoproo.legendsawaken.jurassic.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record HybridizingRecipeInput(ItemStack dnaInputA, ItemStack dnaInputB) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> dnaInputA;
            case 1 -> dnaInputB;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 2;
    }
}
