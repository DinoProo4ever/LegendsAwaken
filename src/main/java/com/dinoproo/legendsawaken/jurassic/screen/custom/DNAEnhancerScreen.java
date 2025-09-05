package com.dinoproo.legendsawaken.jurassic.screen.custom;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DNAEnhancerScreen extends HandledScreen<DNAEnhancerScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(LegendsAwaken.MOD_ID, "textures/gui/container/dna_enhancer.png");
    private static final Identifier DNA_PROGRESS_LEFT_TEXTURE =
            Identifier.of(LegendsAwaken.MOD_ID, "textures/gui/sprites/container/dna_progress_left.png");
    private static final Identifier DNA_PROGRESS_RIGHT_TEXTURE =
            Identifier.of(LegendsAwaken.MOD_ID, "textures/gui/sprites/container/dna_progress_right.png");

    private static final Identifier DNA =
            Identifier.of(LegendsAwaken.MOD_ID, "textures/gui/slot/dna.png");


    public DNAEnhancerScreen(DNAEnhancerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderGrayItems(context, x, y);
        renderDNAProgress(context, x, y);
    }

    private void renderDNAProgress(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            int width = handler.getScaledDNAProgress();
            context.drawTexture(DNA_PROGRESS_LEFT_TEXTURE, x + 34, y + 31, 0, 0,
                    width, 24, 41, 24);
            context.drawTexture(DNA_PROGRESS_RIGHT_TEXTURE, x + 142 - width, y + 31, 41 - width, 0,
                    width, 24, 41, 24);
        }
    }

    private void renderGrayItems(DrawContext context, int x, int y) {
        if (handler.getSlot(0).getStack().isEmpty()) {
            context.drawTexture(DNA, x + 54, y + 24, 0, 0, 16, 16, 16, 16);
        }
        if (handler.getSlot(1).getStack().isEmpty()) {
            context.drawTexture(DNA, x + 54, y + 44, 0, 0, 16, 16, 16, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
