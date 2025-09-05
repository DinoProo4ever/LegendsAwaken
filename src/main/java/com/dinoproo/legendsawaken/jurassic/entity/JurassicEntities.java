package com.dinoproo.legendsawaken.jurassic.entity;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.jurassic.entity.custom.BRCEntity;
import com.dinoproo.legendsawaken.jurassic.entity.custom.VLCEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JurassicEntities {
    public static final EntityType<VLCEntity> VELOCIRAPTOR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LegendsAwaken.MOD_ID, "velociraptor"),
            EntityType.Builder.create(VLCEntity::new, SpawnGroup.CREATURE).dimensions(6f, 14f).build());

    public static final EntityType<BRCEntity> BRACHIOSAURUS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LegendsAwaken.MOD_ID, "brachiosaurus"),
            EntityType.Builder.create(BRCEntity::new, SpawnGroup.CREATURE).build());

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(VELOCIRAPTOR, VLCEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(BRACHIOSAURUS, BRCEntity.setAttributes());
    }

    public static void registerJurassicEntities() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Entities for " + LegendsAwaken.MOD_ID);
        registerAttributes();
    }
}
