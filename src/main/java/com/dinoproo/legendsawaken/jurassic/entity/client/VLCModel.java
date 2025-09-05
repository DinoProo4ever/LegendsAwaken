package com.dinoproo.legendsawaken.jurassic.entity.client;

import com.dinoproo.legendsawaken.LegendsAwaken;
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

public class VLCModel extends GeoModel<VLCEntity> {
    private static final Map<VLCEntity.Variant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(VLCEntity.Variant.class), map -> {
                for (VLCEntity.Variant variant : VLCEntity.Variant.values()) {
                    map.put(variant, Identifier.of(LegendsAwaken.MOD_ID, String.format(Locale.ROOT, "textures/entity/jurassic/velociraptor/vlc_%s.png", variant.getName())));
                }
            });

    @Override
    public Identifier getModelResource(VLCEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "geo/jurassic/velociraptor.geo.json");
    }

    @Override
    public Identifier getTextureResource(VLCEntity animatable) {
        return LOCATION_BY_VARIANT.get(animatable.getVariant());
    }

    @Override
    public Identifier getAnimationResource(VLCEntity animatable) {
        return Identifier.of(LegendsAwaken.MOD_ID, "animations/jurassic/velociraptor.animation.json");
    }

    @Override
    public void setCustomAnimations(VLCEntity animatable, long instanceId, AnimationState<VLCEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            assert entityData != null;
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
