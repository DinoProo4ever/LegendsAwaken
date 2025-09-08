package com.dinoproo.legendsawaken.jurassic.block.entity.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.FenceGateBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FenceGateModel extends GeoModel<FenceGateBlockEntity> {
    @Override
    public Identifier getModelResource(FenceGateBlockEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "geo/jurassic/fence_gate.geo.json");
    }

    @Override
    public Identifier getTextureResource(FenceGateBlockEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "textures/entity/jurassic/fence_gate/fence_gate.png");
    }

    @Override
    public Identifier getAnimationResource(FenceGateBlockEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "animations/jurassic/fence_gate.animation.json");
    }
}
