package com.dinoproo.legendsawaken.compat;

import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.recipe.CultivatingRecipe;
import com.dinoproo.legendsawaken.jurassic.recipe.ExtractingRecipe;
import com.dinoproo.legendsawaken.jurassic.recipe.HybridizingRecipe;
import com.dinoproo.legendsawaken.jurassic.recipe.JurassicRecipes;
import com.dinoproo.legendsawaken.jurassic.screen.custom.CultivatorScreen;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAExtractorScreen;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAHybridizerScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class LegendsAwakenREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ExtractingCategory());
        registry.addWorkstations(ExtractingCategory.EXTRACTING, EntryStacks.of(JurassicBlocks.DNA_EXTRACTOR));

        registry.add(new HybridizingCategory());
        registry.addWorkstations(HybridizingCategory.HYBRIDIZING, EntryStacks.of(JurassicBlocks.DNA_HYBRIDIZER));

        registry.add(new CultivatingCategory());
        registry.addWorkstations(CultivatingCategory.CULTIVATING, EntryStacks.of(JurassicBlocks.CULTIVATOR));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78, ((screen.height - 166) / 2) + 30, 20, 25), DNAExtractorScreen.class, ExtractingCategory.EXTRACTING);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78, ((screen.height - 166) / 2) + 30, 20, 25), DNAHybridizerScreen.class, HybridizingCategory.HYBRIDIZING);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78, ((screen.height - 166) / 2) + 30, 20, 25), CultivatorScreen.class, CultivatingCategory.CULTIVATING);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ExtractingRecipe.class, JurassicRecipes.EXTRACTING_TYPE, ExtractingDisplay::new);
        registry.registerRecipeFiller(HybridizingRecipe.class, JurassicRecipes.HYBRIDIZING_TYPE, HybridizingDisplay::new);
        registry.registerRecipeFiller(CultivatingRecipe.class, JurassicRecipes.CULTIVATING_TYPE, CultivatingDisplay::new);
    }
}
