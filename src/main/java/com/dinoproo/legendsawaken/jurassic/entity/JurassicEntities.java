package com.dinoproo.legendsawaken.jurassic.entity;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.entity.custom.LegendsEntity;
import com.dinoproo.legendsawaken.jurassic.entity.custom.BRCEntity;
import com.dinoproo.legendsawaken.jurassic.entity.custom.VLCEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JurassicEntities {
    private static final Map<Identifier, EntityType<? extends LegendsEntity>> ALL = new HashMap<>();

    public static final EntityType<VLCEntity> VELOCIRAPTOR = register("velociraptor",
            EntityType.Builder.create(VLCEntity::new, SpawnGroup.CREATURE).build());

    public static final EntityType<BRCEntity> BRACHIOSAURUS = register("brachiosaurus",
            EntityType.Builder.create(BRCEntity::new, SpawnGroup.CREATURE).build());

    private static <T extends LegendsEntity> EntityType<T> register(String name, EntityType<T> type) {
        Identifier id = Identifier.of(LegendsAwaken.MOD_ID, name);
        Registry.register(Registries.ENTITY_TYPE, id, type);
        ALL.put(id, type);
        return type;
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(VELOCIRAPTOR, VLCEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(BRACHIOSAURUS, BRCEntity.setAttributes());
    }

    public static void registerJurassicEntities() {
        LegendsAwaken.LOGGER.info("Registering Jurassic Park Entities for " + LegendsAwaken.MOD_ID);
        registerAttributes();
    }

    public static Map<Identifier, EntityType<? extends LegendsEntity>> getAll() {
        return Collections.unmodifiableMap(ALL);
    }
}
