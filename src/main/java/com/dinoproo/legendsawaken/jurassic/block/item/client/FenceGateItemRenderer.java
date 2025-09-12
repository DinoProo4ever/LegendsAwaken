package com.dinoproo.legendsawaken.jurassic.block.item.client;

import com.dinoproo.legendsawaken.jurassic.block.item.custom.FenceGateBlockItem;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FenceGateItemRenderer extends GeoItemRenderer<FenceGateBlockItem> {
    public FenceGateItemRenderer() {
        super(new FenceGateItemModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, FenceGateBlockItem animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        poseStack.translate(0.41, 0.1, 0.41);
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, MatrixStack poseStack, FenceGateBlockItem animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        super.scaleModelForRender(0.18f, 0.18f, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
