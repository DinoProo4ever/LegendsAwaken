package com.dinoproo.legendsawaken.command;

import com.dinoproo.legendsawaken.component.ModComponents;
import com.dinoproo.legendsawaken.player.PlayerStatsComponent;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

public class StatsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("/stats")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    PlayerStatsComponent stats = ModComponents.STATS.get(Objects.requireNonNull(context.getSource().getPlayer()));

                    context.getSource().sendFeedback(() -> Text.literal(
                            "Level: " + stats.getLevel() +
                                    "\nXP: " + stats.getXp() +
                                    "\nAvailable Points: " + stats.getAvailablePoints() +
                                    "\nHealth Level: " + stats.getHealthLevel() +
                                    "\nOxygen Level: " + stats.getOxygenLevel() +
                                    "\nMelee Level: " + stats.getMeleeLevel() +
                                    "\nFortitude Level: " + stats.getFortitudeLevel()
                    ), false);

                    return 1;
                })
                .then(CommandManager.literal("addxp")
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
                                .executes(ctx -> {
                                    var player = Objects.requireNonNull(ctx.getSource().getPlayer());
                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");
                                    PlayerStatsComponent stats = ModComponents.STATS.get(Objects.requireNonNull(ctx.getSource().getPlayer()));

                                    stats.addXp(player, amount);
                                    stats.applyModifiers(player);

                                    ctx.getSource().sendFeedback(() ->
                                            Text.literal("Added " + amount + " XP."), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("setxp")
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(0))
                                .executes(ctx -> {
                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");
                                    PlayerEntity player = Objects.requireNonNull(ctx.getSource().getPlayer());
                                    PlayerStatsComponent stats = ModComponents.STATS.get(player);

                                    stats.setXp(player, amount);
                                    stats.applyModifiers(player);

                                    ctx.getSource().sendFeedback(() ->
                                            Text.literal("XP set to " + amount + "."), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("addpoints")
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
                                .executes(ctx -> {
                                    int amount = IntegerArgumentType.getInteger(ctx, "amount");
                                    PlayerEntity player = Objects.requireNonNull(ctx.getSource().getPlayer());
                                    PlayerStatsComponent stats = ModComponents.STATS.get(player);

                                    stats.addPoints(player, amount);

                                    ctx.getSource().sendFeedback(() ->
                                            Text.literal("Added " + amount + " available points."), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("increase")
                        .then(CommandManager.argument("attribute", StringArgumentType.word())
                                .executes(ctx -> {
                                    String attributeName = StringArgumentType.getString(ctx, "attribute").toUpperCase();
                                    PlayerStatsComponent.Attribute attr = PlayerStatsComponent.Attribute.valueOf(attributeName);

                                    PlayerStatsComponent stats = ModComponents.STATS.get(Objects.requireNonNull(ctx.getSource().getPlayer()));
                                    if (stats.increaseAttributeLevel(ctx.getSource().getPlayer(), attr)) {
                                        ctx.getSource().sendFeedback(() -> Text.literal("Increased " + attributeName), false);
                                    } else {
                                        ctx.getSource().sendFeedback(() -> Text.literal("Not enough points!"), false);
                                    }
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("reset")
                        .executes(ctx -> {
                            PlayerEntity player = Objects.requireNonNull(ctx.getSource().getPlayer());
                            PlayerStatsComponent stats = ModComponents.STATS.get(player);

                            stats.resetStats(player);
                            ctx.getSource().sendFeedback(() ->
                                    Text.literal("Player stats reset."), false);
                            return 1;
                        })
                )
        );
    }
}
