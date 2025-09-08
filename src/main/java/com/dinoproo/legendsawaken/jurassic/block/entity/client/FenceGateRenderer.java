package com.dinoproo.legendsawaken.jurassic.block.entity.client;

import com.dinoproo.legendsawaken.jurassic.block.entity.custom.FenceGateBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FenceGateRenderer extends GeoBlockRenderer<FenceGateBlockEntity> {
    public FenceGateRenderer() {
        super(new FenceGateModel());
    }
}
