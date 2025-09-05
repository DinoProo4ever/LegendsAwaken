package com.dinoproo.legendsawaken.mixin;

import com.dinoproo.legendsawaken.xp.ModXpEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {
    @Shadow private PlayerEntity player;

    @Inject(method = "onCrafted(Lnet/minecraft/item/ItemStack;I)V", at = @At("TAIL"))
    private void legendsawaken$xpOnCraftAmount(ItemStack stack, int amount, CallbackInfo ci) {
        if (this.player instanceof ServerPlayerEntity serverPlayer) {
            ModXpEvents.giveXp(serverPlayer, 0.2 * amount);
        }
    }

    @Inject(method = "onCrafted(Lnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void legendsawaken$xpOnCraft(ItemStack stack, CallbackInfo ci) {
        if (this.player instanceof ServerPlayerEntity serverPlayer) {
            ModXpEvents.giveXp(serverPlayer, 0.2);
        }
    }
}
