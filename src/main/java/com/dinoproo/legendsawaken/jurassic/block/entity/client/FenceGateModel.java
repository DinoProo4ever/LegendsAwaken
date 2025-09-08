package com.dinoproo.legendsawaken.jurassic.block.entity.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.block.entity.custom.FenceGateBlockEntity;
import com.dinoproo.legendsawaken.jurassic.entity.custom.VLCEntity;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Locale;
import java.util.Map;

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
