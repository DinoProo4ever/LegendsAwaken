package com.dinoproo.legendsawaken.xp;

import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import com.dinoproo.legendsawaken.player.PlayerStatsComponent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModXpEvents {

    public static void register() {
        registerBlockBreakXp();
        registerMobKillXp();
    }

    private static final Map<String, Double> BLOCK_XP = Map.ofEntries(
            Map.entry("coal_ore", 1d),
            Map.entry("copper_ore", 2d),
            Map.entry("iron_ore", 3d),
            Map.entry("nether_gold_ore", 3d),
            Map.entry("nether_quartz_ore", 4d),
            Map.entry("amber_ore", 5d),
            Map.entry("redstone_ore", 8d),
            Map.entry("lapis_ore", 9d),
            Map.entry("gold_ore", 9d),
            Map.entry("rich_amber_ore", 9d),
            Map.entry("diamond_ore", 27d),
            Map.entry("ancient_debris", 81d)
    );


    private static void registerBlockBreakXp() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient() || !(player instanceof ServerPlayerEntity serverPlayer)) return;

            Identifier id = Registries.BLOCK.getId(state.getBlock());
            double xp = BLOCK_XP.getOrDefault(id.getPath(), 0.2d);

            if (xp > 0) {
                giveXp(serverPlayer, xp);
            }
        });
    }

    private static final Map<EntityType<?>, Double> ENTITY_XP = Map.<EntityType<?>, Double>ofEntries(
            //Passive
            Map.entry(EntityType.ALLAY, 5d),
            Map.entry(EntityType.CAT, 5d),
            Map.entry(EntityType.OCELOT, 5d),
            Map.entry(EntityType.PARROT, 2.5d),
            Map.entry(EntityType.SNOW_GOLEM, 5d),

            Map.entry(EntityType.ARMADILLO, 5d),
            Map.entry(EntityType.TURTLE, 20d),
            Map.entry(EntityType.CHICKEN, 5d),
            Map.entry(EntityType.COW, 10d),
            Map.entry(EntityType.MOOSHROOM, 25d),
            Map.entry(EntityType.PIG, 10d),
            Map.entry(EntityType.SHEEP, 10d),

            Map.entry(EntityType.AXOLOTL, 5d),
            Map.entry(EntityType.FROG, 5d),
            Map.entry(EntityType.TADPOLE, 2d),
            Map.entry(EntityType.SNIFFER, 25d),

            Map.entry(EntityType.BAT, 2.5d),
            Map.entry(EntityType.COD, 2.5d),
            Map.entry(EntityType.SALMON, 2.5d),
            Map.entry(EntityType.TROPICAL_FISH, 2.5d),
            Map.entry(EntityType.PUFFERFISH, 5d),
            Map.entry(EntityType.SQUID, 10d),
            Map.entry(EntityType.GLOW_SQUID, 15d),
            Map.entry(EntityType.RABBIT, 5d),

            Map.entry(EntityType.CAMEL, 20d),
            Map.entry(EntityType.HORSE, 20d),
            Map.entry(EntityType.DONKEY, 20d),
            Map.entry(EntityType.MULE, 25d),
            Map.entry(EntityType.SKELETON_HORSE, 30d),
            Map.entry(EntityType.STRIDER, 20d),

            Map.entry(EntityType.VILLAGER, 10d),
            Map.entry(EntityType.WANDERING_TRADER, 15d),

            //Neutral
            Map.entry(EntityType.BEE, 7.5d),
            Map.entry(EntityType.DOLPHIN, 15d),
            Map.entry(EntityType.FOX, 10d),
            Map.entry(EntityType.GOAT, 10d),
            Map.entry(EntityType.LLAMA, 10d),
            Map.entry(EntityType.TRADER_LLAMA, 15d),
            Map.entry(EntityType.PANDA, 10d),
            Map.entry(EntityType.POLAR_BEAR, 10d),

            Map.entry(EntityType.SPIDER, 10d),
            Map.entry(EntityType.CAVE_SPIDER, 15d),

            Map.entry(EntityType.DROWNED, 10d),
            Map.entry(EntityType.ENDERMAN, 15d),
            Map.entry(EntityType.PIGLIN, 10d),
            Map.entry(EntityType.ZOMBIFIED_PIGLIN, 10d),

            Map.entry(EntityType.IRON_GOLEM, 30d),
            Map.entry(EntityType.WOLF, 10d),

            //Hostile
            Map.entry(EntityType.BLAZE, 15d),
            Map.entry(EntityType.SKELETON, 10d),
            Map.entry(EntityType.BOGGED, 10d),
            Map.entry(EntityType.STRAY, 10d),
            Map.entry(EntityType.WITHER_SKELETON, 20d),
            Map.entry(EntityType.BREEZE, 15d),
            Map.entry(EntityType.CREEPER, 15d),
            Map.entry(EntityType.SILVERFISH, 10d),
            Map.entry(EntityType.ENDERMITE, 15d),
            Map.entry(EntityType.PILLAGER, 15d),
            Map.entry(EntityType.VINDICATOR, 20d),
            Map.entry(EntityType.VEX, 10d),
            Map.entry(EntityType.EVOKER, 25d),
            Map.entry(EntityType.GHAST, 20d),
            Map.entry(EntityType.GUARDIAN, 15d),
            Map.entry(EntityType.HOGLIN, 20d),
            Map.entry(EntityType.ZOGLIN, 20d),
            Map.entry(EntityType.RAVAGER, 30d),
            Map.entry(EntityType.SHULKER, 20d),
            Map.entry(EntityType.ZOMBIE, 10d),
            Map.entry(EntityType.HUSK, 10d),
            Map.entry(EntityType.ZOMBIE_VILLAGER, 10d),
            Map.entry(EntityType.WITCH, 20d),

            Map.entry(EntityType.ELDER_GUARDIAN, 250d),
            Map.entry(EntityType.WARDEN, 500d),

            Map.entry(EntityType.SLIME, 5d),
            Map.entry(EntityType.MAGMA_CUBE, 5d),

            //Boss
            Map.entry(EntityType.WITHER, 750d),
            Map.entry(EntityType.ENDER_DRAGON, 1500d),

            //Unused
            Map.entry(EntityType.GIANT, 120d),
            Map.entry(EntityType.ILLUSIONER, 25d),
            Map.entry(EntityType.ZOMBIE_HORSE, 30d),

            //Legends Awaken
            //Jurassic Park
            //Neutral
            Map.entry(JurassicEntities.BRACHIOSAURUS, 120d),

            //Hostile
            Map.entry(JurassicEntities.VELOCIRAPTOR, 20d)
    );

    private static void registerMobKillXp() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity.getWorld().isClient()) return;

            if (!(damageSource.getAttacker() instanceof ServerPlayerEntity serverPlayer)) return;

            double xp = ENTITY_XP.getOrDefault(entity.getType(), 5d);
            if (xp > 0) {
                giveXp(serverPlayer, xp);
            }
        });
    }


    public static void giveXp(PlayerEntity player, double amount) {
        PlayerStatsComponent stats = PlayerStatsComponent.get(player);
        stats.addXp(player, amount);
        stats.sync(player);
        player.sendMessage(Text.literal("§a+ " + amount + " XP"), true);
    }
}
