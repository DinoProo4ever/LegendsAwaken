package com.dinoproo.legendsawaken.block.item.client;

import com.dinoproo.legendsawaken.block.item.custom.GiantFernBlockItem;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.world.biome.FoliageColors;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.Color;

public class GiantFernItemRenderer extends GeoItemRenderer<GiantFernBlockItem> {
    public GiantFernItemRenderer() {
        super(new GiantFernItemModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, GiantFernBlockItem animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        poseStack.translate(0.38, 0.05, 0.38);
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, MatrixStack poseStack, GiantFernBlockItem animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        super.scaleModelForRender(0.24f, 0.24f, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }

    @Override
    public Color getRenderColor(GiantFernBlockItem animatable, float partialTick, int packedLight) {
        return new Color(FoliageColors.getDefaultColor());
    }
}
