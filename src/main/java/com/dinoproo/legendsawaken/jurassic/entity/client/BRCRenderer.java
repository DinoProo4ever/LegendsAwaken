package com.dinoproo.legendsawaken.jurassic.entity.client;

import com.dinoproo.legendsawaken.jurassic.entity.custom.BRCEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BRCRenderer extends GeoEntityRenderer<BRCEntity> {
    public BRCRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BRCModel());
    }

    @Override
    public void render(BRCEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (!entity.isBaby()){
            poseStack.scale(4f, 4f, 4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
