package com.dinoproo.legendsawaken.jurassic.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.world.World;

public record ExtractingRecipeInput(ItemStack input, World world) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }

    @Override
    public int getSize() {
        return 1;
    }

    public World getWorld () {
        return world();
    }
}
