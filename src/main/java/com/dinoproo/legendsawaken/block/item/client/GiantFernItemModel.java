package com.dinoproo.legendsawaken.block.item.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.item.custom.GiantFernBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GiantFernItemModel extends GeoModel<GiantFernBlockItem> {
    @Override
    public Identifier getModelResource(GiantFernBlockItem animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "geo/giant_fern.geo.json");
    }

    @Override
    public Identifier getTextureResource(GiantFernBlockItem animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "textures/entity/giant_fern.png");
    }

    @Override
    public Identifier getAnimationResource(GiantFernBlockItem animatable) {
        return null;
    }
}
