package com.dinoproo.legendsawaken.block.entity.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.block.entity.custom.GiantFernBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GiantFernModel extends GeoModel<GiantFernBlockEntity> {
    @Override
    public Identifier getModelResource(GiantFernBlockEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "geo/giant_fern.geo.json");
    }

    @Override
    public Identifier getTextureResource(GiantFernBlockEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "textures/entity/giant_fern.png");
    }

    @Override
    public Identifier getAnimationResource(GiantFernBlockEntity animatable) {
        return null;
    }
}
