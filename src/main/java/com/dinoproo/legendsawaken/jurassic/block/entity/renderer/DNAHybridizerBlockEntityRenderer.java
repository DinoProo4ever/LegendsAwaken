package com.dinoproo.legendsawaken.jurassic.block.entity.renderer;

import com.dinoproo.legendsawaken.jurassic.block.entity.custom.DNAHybridizerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Objects;

public class DNAHybridizerBlockEntityRenderer implements BlockEntityRenderer<DNAHybridizerBlockEntity> {
    public DNAHybridizerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(DNAHybridizerBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack;
        if (!entity.getStack(2).isEmpty()) {
            stack = entity.getStack(2);
        } else if (!entity.getStack(0).isEmpty()){
            stack = entity.getStack(0);
        } else {
            stack = entity.getStack(1);
        }

        matrices.push();
        matrices.translate(0.5f, 0.5625f, 0.71875f);
        matrices.scale(0.5f, 0.5f, 0.5f);

        itemRenderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(Objects.requireNonNull(entity.getWorld()),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos.up());
        int sLight = world.getLightLevel(LightType.SKY, pos.up()        );
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
