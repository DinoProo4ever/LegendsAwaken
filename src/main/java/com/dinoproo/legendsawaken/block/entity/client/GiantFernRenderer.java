package com.dinoproo.legendsawaken.block.entity.client;

import com.dinoproo.legendsawaken.block.entity.custom.GiantFernBlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.FoliageColors;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.util.Color;

public class GiantFernRenderer extends GeoBlockRenderer<GiantFernBlockEntity> {
    public GiantFernRenderer() {
        super(new GiantFernModel());
    }

    @Override
    public Color getRenderColor(GiantFernBlockEntity animatable, float partialTick, int packedLight) {
        if (animatable.getWorld() != null) {
            BlockPos pos = animatable.getPos();
            int mcColor = BiomeColors.getGrassColor(animatable.getWorld(), pos);
            return new Color(mcColor);
        }

        return new Color(FoliageColors.getDefaultColor());
    }
}
