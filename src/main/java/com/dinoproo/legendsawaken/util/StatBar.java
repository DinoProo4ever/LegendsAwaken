package com.dinoproo.legendsawaken.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class StatBar {
    private final String name;
    private final int current;
    private final int max;
    private final int color;
    private final boolean isPercent;

    public StatBar(String name, int current, int max, int color) {
        this(name, current, max, color, false);
    }

    public StatBar(String name, int current, int max, int color, boolean isPercent) {
        this.name = name;
        this.current = current;
        this.max = max;
        this.color = color;
        this.isPercent = isPercent;
    }

    public void render(DrawContext context, TextRenderer renderer, int x, int y) {
        int barWidth = 140;
        int barHeight = 1;
        float ratio = (float) current / max;
        int filled = (int) (barWidth * ratio);

        context.drawText(renderer, Text.literal(name), x, y, color, true);

        context.fill(x, y + 10, x + barWidth, y + 10 + barHeight, 0xFF00AAAA);
        context.fill(x, y + 10, x + filled, y + 10 + barHeight, 0xFF55FFFF);

        String value = isPercent ? current + "%" : current + "/" + max;
        int valueWidth = renderer.getWidth(value);
        context.drawText(renderer, Text.literal(value), x + barWidth - valueWidth, y, 0xAAAAAA, true);
    }

    public StatBar increment(int amount) {
        int newValue = Math.min(current + amount, max);
        return new StatBar(name, newValue, max, color, isPercent);
    }
}
