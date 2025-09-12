package com.dinoproo.legendsawaken.datagen;

import com.dinoproo.legendsawaken.block.ModBlocks;
import com.dinoproo.legendsawaken.item.ModItems;
import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import com.dinoproo.legendsawaken.tensura.item.TenSuraItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelGenerator extends FabricModelProvider {
    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(ModBlocks.REINFORCED_GLASS);

        generator.registerLog(ModBlocks.SEQUOIA_LOG).log(ModBlocks.SEQUOIA_LOG).wood(ModBlocks.SEQUOIA_WOOD);
        generator.registerLog(ModBlocks.STRIPPED_SEQUOIA_LOG).log(ModBlocks.STRIPPED_SEQUOIA_LOG).wood(ModBlocks.STRIPPED_SEQUOIA_WOOD);

        BlockStateModelGenerator.BlockTexturePool sequoiaPool = generator.registerCubeAllModelTexturePool(ModBlocks.SEQUOIA_PLANKS);
        sequoiaPool.stairs(ModBlocks.SEQUOIA_STAIRS);
        sequoiaPool.slab(ModBlocks.SEQUOIA_SLAB);
        sequoiaPool.fence(ModBlocks.SEQUOIA_FENCE);
        sequoiaPool.fenceGate(ModBlocks.SEQUOIA_FENCE_GATE);
        generator.registerDoor(ModBlocks.SEQUOIA_DOOR);
        generator.registerTrapdoor(ModBlocks.SEQUOIA_TRAPDOOR);
        sequoiaPool.pressurePlate(ModBlocks.SEQUOIA_PRESSURE_PLATE);
        sequoiaPool.button(ModBlocks.SEQUOIA_BUTTON);

        generator.registerSingleton(ModBlocks.SEQUOIA_LEAVES, TexturedModel.LEAVES);
        generator.registerTintableCrossBlockState(ModBlocks.SEQUOIA_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool calcitePool = generator.registerCubeAllModelTexturePool(Blocks.CALCITE);
        calcitePool.stairs(ModBlocks.CALCITE_STAIRS);
        calcitePool.slab(ModBlocks.CALCITE_SLAB);
        calcitePool.wall(ModBlocks.CALCITE_WALL);

        BlockStateModelGenerator.BlockTexturePool steelPool = generator.registerCubeAllModelTexturePool(ModBlocks.STEEL_BLOCK);
        steelPool.stairs(ModBlocks.STEEL_STAIRS);
        generator.registerDoor(ModBlocks.STEEL_DOOR);

        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_BLOCK_XS);
        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_BLOCK_S);
        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_BLOCK_M);
        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_BLOCK_L);
        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_BLOCK_XL);

        generator.registerSimpleCubeAll(JurassicBlocks.AMBER_ORE);
        generator.registerSimpleCubeAll(JurassicBlocks.RICH_AMBER_ORE);
        generator.registerSimpleCubeAll(JurassicBlocks.DEEPSLATE_AMBER_ORE);
        generator.registerSimpleCubeAll(JurassicBlocks.DEEPSLATE_RICH_AMBER_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ModItems.CHITIN, Models.GENERATED);
        generator.register(ModItems.KERATIN, Models.GENERATED);
        generator.register(ModItems.SILICA_PEARLS, Models.GENERATED);
        generator.register(ModItems.BLACK_PEARL, Models.GENERATED);

        generator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        generator.register(ModItems.CEMENTING_PASTE, Models.GENERATED);
        generator.register(ModItems.ELECTRONICS, Models.GENERATED);

        generator.register(ModItems.HOLO_EMITTER, Models.GENERATED);

        generator.register(ModItems.POLYMER, Models.GENERATED);
        generator.register(ModItems.ORGANIC_POLYMER, Models.GENERATED);

        generator.register(ModItems.ELEMENT, Models.GENERATED);
        generator.register(ModItems.ELEMENT_SHARD, Models.GENERATED);
        generator.register(ModItems.ELEMENT_DUST, Models.GENERATED);

        generator.register(ModItems.WRENCH, Models.HANDHELD);

        generator.register(ModItems.SYRINGE, Models.HANDHELD);
        generator.register(ModItems.GROWTH_INHIBITOR, Models.HANDHELD);
        generator.register(ModItems.GROWTH_SERUM, Models.HANDHELD);

        generator.register(ModBlocks.SEQUOIA_SAPLING.asItem(), Models.GENERATED);

        generator.register(JurassicItems.SYNTETIC_EGG, Models.GENERATED);

        generator.register(JurassicItems.AMBER_XS, Models.GENERATED);
        generator.register(JurassicItems.AMBER_S, Models.GENERATED);
        generator.register(JurassicItems.AMBER_M, Models.GENERATED);
        generator.register(JurassicItems.AMBER_L, Models.GENERATED);
        generator.register(JurassicItems.AMBER_XL, Models.GENERATED);

        generator.register(JurassicItems.MUSIC_DISC_JP_THEME, Models.GENERATED);

        generator.register(JurassicItems.RAPTOR_LEG, Models.GENERATED);
        generator.register(JurassicItems.COOKED_RAPTOR_LEG, Models.GENERATED);

        generator.register(JurassicItems.DNA, Models.GENERATED);
        generator.register(JurassicItems.HYBRID_DNA, Models.GENERATED);

        generator.register(JurassicBlocks.VLC_EGG.asItem(), Models.GENERATED);

        generator.register(JurassicItems.VLC_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        generator.register(JurassicItems.BRC_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        generator.register(TenSuraItems.MAGICRYSTAL, Models.GENERATED);
    }
}
