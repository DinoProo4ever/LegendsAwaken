package com.dinoproo.legendsawaken.jurassic.screen;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.screen.custom.CultivatorScreenHandler;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAEnhancerScreenHandler;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAExtractorScreenHandler;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAHybridizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class JurassicScreenHandlers {
    public static final ScreenHandlerType<DNAExtractorScreenHandler> DNA_EXTRACTOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(LegendsAwaken.MOD_ID, "dna_extractor_screen_handler"),
                    new ExtendedScreenHandlerType<>(DNAExtractorScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<DNAEnhancerScreenHandler> DNA_ENHANCER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(LegendsAwaken.MOD_ID, "dna_enhancer_screen_handler"),
                    new ExtendedScreenHandlerType<>(DNAEnhancerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<DNAHybridizerScreenHandler> DNA_HYBRIDIZER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(LegendsAwaken.MOD_ID, "dna_hybridizer_screen_handler"),
                    new ExtendedScreenHandlerType<>(DNAHybridizerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CultivatorScreenHandler> CULTIVATOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(LegendsAwaken.MOD_ID, "cultivator_screen_handler"),
                    new ExtendedScreenHandlerType<>(CultivatorScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Screen Handlers for " + LegendsAwaken.MOD_ID);
    }
}
