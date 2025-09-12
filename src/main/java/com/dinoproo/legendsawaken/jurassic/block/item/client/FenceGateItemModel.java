package com.dinoproo.legendsawaken.jurassic.block.item.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.item.custom.FenceGateBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FenceGateItemModel extends GeoModel<FenceGateBlockItem> {
    @Override
    public Identifier getModelResource(FenceGateBlockItem animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "geo/jurassic/fence_gate.geo.json");
    }

    @Override
    public Identifier getTextureResource(FenceGateBlockItem animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "textures/entity/jurassic/fence_gate/fence_gate.png");
    }

    @Override
    public Identifier getAnimationResource(FenceGateBlockItem animatable) {
        return null;
    }
}
