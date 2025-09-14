package com.dinoproo.legendsawaken.mixin;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.ModWoodTypes;
import net.minecraft.block.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;

@Mixin(WoodType.class)
public class WoodTypeMixin {
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void registerCustomWoodType(CallbackInfo ci) {
        try {
            Method register = WoodType.class.getDeclaredMethod("register", WoodType.class);
            register.setAccessible(true);
            ModWoodTypes.SEQUOIA = ModWoodTypes.createSequoia();
            register.invoke(null, ModWoodTypes.SEQUOIA);
        } catch (Exception e) {
            LegendsAwaken.LOGGER.error("Failed to register Sequoia WoodType!", e);
        }
    }
}
