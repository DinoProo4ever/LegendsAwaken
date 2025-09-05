package com.dinoproo.legendsawaken;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.command.StatsCommand;
import com.dinoproo.legendsawaken.component.ModComponents;
import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.item.ModItemGroups;
import com.dinoproo.legendsawaken.item.ModItems;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import com.dinoproo.legendsawaken.jurassic.entity.JurassicEntities;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import com.dinoproo.legendsawaken.jurassic.recipe.JurassicRecipes;
import com.dinoproo.legendsawaken.jurassic.screen.JurassicScreenHandlers;
import com.dinoproo.legendsawaken.jurassic.sound.JurassicSounds;
import com.dinoproo.legendsawaken.jurassic.util.JurassicSpecies;
import com.dinoproo.legendsawaken.player.ModPlayerEntityHandler;
import com.dinoproo.legendsawaken.player.PlayerStatsComponent;
import com.dinoproo.legendsawaken.screen.ModScreenHandlers;
import com.dinoproo.legendsawaken.tensura.item.TenSuraItems;
import com.dinoproo.legendsawaken.world.gen.ModWorldGeneration;
import com.dinoproo.legendsawaken.xp.ModXpEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegendsAwaken implements ModInitializer {
	public static final String MOD_ID = "legendsawaken";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			PlayerStatsComponent stats = ModComponents.STATS.get(handler.getPlayer());
			stats.sync(handler.getPlayer());
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> StatsCommand.register(dispatcher));

		ModPlayerEntityHandler.registerPlayerEntityHandler();
		ModXpEvents.register();

		ModItemGroups.registerItemGroups();

		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModDataComponents.registerDataComponents();

		FuelRegistry.INSTANCE.add(ModItems.ELEMENT, 32000);

		ModScreenHandlers.registerScreenHandlers();

		ModWorldGeneration.generateModWorldGen();


		JurassicItems.registerJurassicItems();
		JurassicBlocks.registerJurassicBlocks();
		JurassicSounds.registerJurassicSounds();

		JurassicSpecies.registerJurassicSpecies();

		JurassicEntities.registerJurassicEntities();

		JurassicBlockEntities.registerBlockEntities();
		JurassicScreenHandlers.registerScreenHandlers();

		JurassicRecipes.registerRecipes();


		TenSuraItems.registerTenSuraItems();
	}
}