package com.dinoproo.legendsawaken.jurassic.entity.client;

import com.dinoproo.legendsawaken.jurassic.entity.custom.VLCEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VLCRenderer extends GeoEntityRenderer<VLCEntity> {
    public VLCRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new VLCModel());
    }

    @Override
    public void render(VLCEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
