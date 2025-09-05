package com.dinoproo.legendsawaken.jurassic.item.custom;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.util.JurassicSpecies;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class DNAItem extends Item {
    public DNAItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        stack.set(ModDataComponents.SPECIES, null);
        stack.set(ModDataComponents.DNA_LEVEL, 100);
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        String speciesId = stack.getOrDefault(ModDataComponents.SPECIES, "unknown");
        JurassicSpecies species = JurassicSpecies.get(Identifier.of(speciesId));
        Text displayName = (species != null) ? species.displayName() :
                Text.translatable("tooltip.legendsawaken.species.unknown").formatted(Formatting.DARK_RED);
        tooltip.add(Text.translatable("tooltip.legendsawaken.species", displayName).formatted(Formatting.DARK_GREEN));

        Integer dnaLevel = stack.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
        tooltip.add(Text.translatable("tooltip.legendsawaken.dna_level", dnaLevel).append("%").formatted(Formatting.DARK_PURPLE));
    }
}
