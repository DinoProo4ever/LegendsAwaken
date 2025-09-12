package com.dinoproo.legendsawaken.screen.custom;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.player.PlayerStatsComponent;
import com.dinoproo.legendsawaken.util.StatBar;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StatsScreen extends Screen {
    private static final Identifier TEXTURE = Identifier.of(LegendsAwaken.MOD_ID, "textures/gui/stats.png");

    private static final ButtonTextures LEVELUP_TEXTURES = new ButtonTextures(
            Identifier.of(LegendsAwaken.MOD_ID, "stats/levelup"),
            Identifier.of(LegendsAwaken.MOD_ID, "stats/levelup_disabled"),
            Identifier.of(LegendsAwaken.MOD_ID, "stats/levelup_highlighted")
    );

    private final PlayerEntity player;
    private final PlayerStatsComponent stats;

    private ButtonWidget healthButton;
    private ButtonWidget oxygenButton;
    private ButtonWidget meleeButton;
    private ButtonWidget fortitudeButton;

    public StatsScreen(PlayerEntity player) {
        super(Text.translatable("screen.legendsawaken.stats"));
        this.player = player;
        this.stats = PlayerStatsComponent.get(player);
    }

    @Override
    protected void init() {
        int x = (this.width - 176) / 2;
        int y = (this.height - 166) / 2;

        int buttonX = x + 153;
        int startY = y + 34;

        assert client != null;
        assert client.player != null;

        healthButton = new TexturedButtonWidget(buttonX, startY+16, 11, 11, LEVELUP_TEXTURES, btn -> {
            client.player.networkHandler.sendChatCommand("/stats increase HEALTH");
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
            updateButtonStates();
        });

        oxygenButton = new TexturedButtonWidget(buttonX, startY + 32, 11, 11, LEVELUP_TEXTURES, btn -> {
            client.player.networkHandler.sendChatCommand("/stats increase OXYGEN");
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
            updateButtonStates();
        });

        meleeButton = new TexturedButtonWidget(buttonX, startY + 48, 11, 11, LEVELUP_TEXTURES, btn -> {
            client.player.networkHandler.sendChatCommand("/stats increase MELEE_DAMAGE");
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
            updateButtonStates();
        });

        fortitudeButton = new TexturedButtonWidget(buttonX, startY + 64, 11, 11, LEVELUP_TEXTURES, btn -> {
            client.player.networkHandler.sendChatCommand("/stats increase FORTITUDE");
            client.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
            updateButtonStates();
        });

        this.addDrawableChild(healthButton);
        this.addDrawableChild(oxygenButton);
        this.addDrawableChild(meleeButton);
        this.addDrawableChild(fortitudeButton);

        updateButtonStates();
    }

    private void updateButtonStates() {
        boolean hasPoints = stats.getAvailablePoints() > 0;

        healthButton.active = hasPoints;
        oxygenButton.active = hasPoints;
        meleeButton.active = hasPoints;
        fortitudeButton.active = hasPoints;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = (this.width - 176) / 2;
        int y = (this.height - 166) / 2;
        int rightEdge = x + 176 - 12;

        context.drawTexture(TEXTURE, x, y, 0, 0, 176, 166);

        context.drawText(textRenderer, Text.literal("Player Stats"), x + 58, y + 6, 0x3F3F3F, false);

        context.drawText(textRenderer, Text.literal("Level: " + stats.getLevel()), x + 12, y + 20, 0x55FFFF, true);

        int pointsWidth = textRenderer.getWidth("Points: " + stats.getAvailablePoints());
        context.drawText(textRenderer, Text.literal("Points: " + stats.getAvailablePoints()), rightEdge - pointsWidth, y + 20, 0x55FFFF, true);

        int currentLevel = stats.getLevel();
        double currentXp = stats.getXp();
        double xpRatio;

        if (currentLevel >= PlayerStatsComponent.MAX_LEVEL) {
            xpRatio = 1.0f;
        } else {
            int nextLevel = currentLevel + 1;
            double xpForNext = PlayerStatsComponent.getXpForLevel(nextLevel);
            xpRatio = xpForNext > 0 ? Math.min(currentXp / xpForNext, 1.0f) : 0.0f;
        }
        if (currentLevel >= PlayerStatsComponent.MAX_LEVEL) {
            context.drawText(textRenderer, Text.literal("XP: MAX LEVEL"), x + 12, y + 34, 0x55FFAA, true);
        } else {
            context.drawText(textRenderer, Text.literal("XP: " + (float)currentXp + " / " + PlayerStatsComponent.getXpForLevel(currentLevel + 1)), x + 12, y + 34, 0x55FFAA, true);
        }

        int xpBarWidth = 152;
        context.fill(x + 12, y + 44, x + 12 + xpBarWidth, y + 45, 0xFF222222);
        context.fill(x + 12, y + 44, x + 12 + (int) (xpRatio * xpBarWidth), y + 45, 0xFF55FFAA);

        float currentHealth = player.getHealth();
        float maxHealth = player.getMaxHealth();

        float currentMelee = (float) (100 * (1 + stats.getMeleeLevel() * 0.05));

        int statStartY = 34;
        new StatBar("Health", (int) currentHealth, (int) maxHealth, 0xFF5555)
                .render(context, textRenderer, x + 12, y + statStartY + 16);


        new StatBar("Oxygen", 100 + stats.getOxygenLevel() * 10, 100 + stats.getOxygenLevel() * 10,
                0x00AAAA, true).render(context, textRenderer, x + 12, y + statStartY + 32);

        new StatBar("Melee Damage", (int) currentMelee, 100 + stats.getMeleeLevel() * 5,
                0xFF5555, true).render(context, textRenderer, x + 12, y + statStartY + 48);

        new StatBar("Fortitude", 100 + stats.getFortitudeLevel() * 5, 100 + stats.getFortitudeLevel() * 5,
                0xAAAAAA, true).render(context, textRenderer, x + 12, y + statStartY + 64);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
