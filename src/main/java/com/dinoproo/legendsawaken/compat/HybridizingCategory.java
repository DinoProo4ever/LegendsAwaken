package com.dinoproo.legendsawaken.compat;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class HybridizingCategory implements DisplayCategory<Display> {
    public static final Identifier TEXTURE = Identifier.of(LegendsAwaken.MOD_ID,
            "textures/gui/container/dna_hybridizer_rei.png");

    public static final CategoryIdentifier<ExtractingDisplay> HYBRIDIZING =
            CategoryIdentifier.of(LegendsAwaken.MOD_ID, "hybridizing");

    @Override
    public CategoryIdentifier<ExtractingDisplay> getCategoryIdentifier() {
        return HYBRIDIZING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.legendsawaken.hybridizing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(JurassicBlocks.DNA_HYBRIDIZER);
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 17, startPoint.y + 33))
                .entries(display.getInputEntries().get(0)).disableBackground().markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 143, startPoint.y + 33))
                .entries(display.getInputEntries().get(1)).disableBackground().markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 33))
                .entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
