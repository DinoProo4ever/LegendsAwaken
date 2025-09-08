package com.dinoproo.legendsawaken;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import com.dinoproo.legendsawaken.jurassic.block.entity.client.CultivatorRenderer;
import com.dinoproo.legendsawaken.jurassic.block.entity.client.DNAHybridizerRenderer;
import com.dinoproo.legendsawaken.jurassic.block.entity.client.FenceGateRenderer;
import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import com.dinoproo.legendsawaken.jurassic.entity.client.BRCRenderer;
import com.dinoproo.legendsawaken.jurassic.entity.client.VLCRenderer;
import com.dinoproo.legendsawaken.jurassic.screen.JurassicScreenHandlers;
import com.dinoproo.legendsawaken.jurassic.screen.custom.CultivatorScreen;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAEnhancerScreen;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAExtractorScreen;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAHybridizerScreen;
import com.dinoproo.legendsawaken.screen.custom.StatsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LegendsAwakenClient implements ClientModInitializer {
    public static KeyBinding openStatsKey;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REINFORCED_GLASS, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_TRAPDOOR, RenderLayer.getTranslucent());

        openStatsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.legendsawaken.open_stats",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.legendsawaken"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openStatsKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new StatsScreen(client.player));
                }
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.LOW_SECURITY_FENCE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.FENCE_GATE, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.DNA_EXTRACTOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.DNA_ENHANCER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.DNA_HYBRIDIZER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JurassicBlocks.CULTIVATOR, RenderLayer.getTranslucent());

        EntityRendererRegistry.register(JurassicEntities.VELOCIRAPTOR, VLCRenderer::new);
        EntityRendererRegistry.register(JurassicEntities.BRACHIOSAURUS, BRCRenderer::new);

        HandledScreens.register(JurassicScreenHandlers.DNA_EXTRACTOR_SCREEN_HANDLER, DNAExtractorScreen::new);

        HandledScreens.register(JurassicScreenHandlers.DNA_ENHANCER_SCREEN_HANDLER, DNAEnhancerScreen::new);

        BlockEntityRendererFactories.register(JurassicBlockEntities.FENCE_GATE_BE, ctx -> new FenceGateRenderer());

        BlockEntityRendererFactories.register(JurassicBlockEntities.DNA_HYBRIDIZER_BE, DNAHybridizerRenderer::new);
        HandledScreens.register(JurassicScreenHandlers.DNA_HYBRIDIZER_SCREEN_HANDLER, DNAHybridizerScreen::new);

        BlockEntityRendererFactories.register(JurassicBlockEntities.CULTIVATOR_BE, CultivatorRenderer::new);
        HandledScreens.register(JurassicScreenHandlers.CULTIVATOR_SCREEN_HANDLER, CultivatorScreen::new);
    }
}
