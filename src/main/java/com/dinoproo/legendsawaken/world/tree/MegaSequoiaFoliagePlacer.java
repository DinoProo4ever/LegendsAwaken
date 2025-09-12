package com.dinoproo.legendsawaken.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class MegaSequoiaFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MegaSequoiaFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> fillFoliagePlacerFields(instance)
                    .and(IntProvider.createValidatingCodec(1, 512)
                            .fieldOf("crown_height")
                            .forGetter(fp -> fp.crownHeight))
                    .apply(instance, MegaSequoiaFoliagePlacer::new)
    );
    private final IntProvider crownHeight;

    public MegaSequoiaFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider crownHeight) {
        super(radius, offset);
        this.crownHeight = crownHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModTreePlacers.MEGA_SEQUOIA_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random,
                            TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode,
                            int foliageHeight, int radius, int offset) {

        BlockPos blockPos = treeNode.getCenter();
        int i = 0;
        int extendedFoliageHeight = MathHelper.ceil(foliageHeight * 1.5f);
        for (int j = blockPos.getY() - extendedFoliageHeight + offset; j <= blockPos.getY() + offset; ++j) {
            int k = blockPos.getY() - j;
            int l = radius + treeNode.getFoliageRadius() + MathHelper.floor((float)k / (float)extendedFoliageHeight * 3.5f);
            int m = k > 0 && l == i && (j & 1) == 0 ? l + 1 : l;
            this.generateSquare(world, placer, random, config, new BlockPos(blockPos.getX(), j, blockPos.getZ()), m, 0, treeNode.isGiantTrunk());
            i = l;
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.crownHeight.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (dx + dz >= 7) {
            return true;
        }
        return dx * dx + dz * dz > radius * radius;
    }
}
