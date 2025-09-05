package com.dinoproo.legendsawaken.player;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.component.ModComponents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class ModPlayerEntityHandler {
    public static void registerPlayerEntityHandler() {
        LegendsAwaken.LOGGER.info("Registering Player Entity Handler for " + LegendsAwaken.MOD_ID);
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            PlayerStatsComponent stats = ModComponents.STATS.get(newPlayer);
            stats.applyModifiers(newPlayer);
            newPlayer.setHealth(newPlayer.getMaxHealth());
        });
    }
}
