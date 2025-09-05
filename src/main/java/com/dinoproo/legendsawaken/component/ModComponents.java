package com.dinoproo.legendsawaken.component;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.player.PlayerStatsComponent;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<PlayerStatsComponent> STATS =
            ComponentRegistry.getOrCreate(
                    Identifier.of(LegendsAwaken.MOD_ID, "player_stats"),
                    PlayerStatsComponent.class
            );

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(STATS, player -> new PlayerStatsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
