package com.dinoproo.legendsawaken.jurassic.block.custom;

import com.dinoproo.legendsawaken.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Objects;

public class FenceBlock extends net.minecraft.block.FenceBlock {
    public static final BooleanProperty HAS_SUPPORT = BooleanProperty.of("has_support");
    public static final BooleanProperty HAS_POST = BooleanProperty.of("has_post");

    public FenceBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HAS_SUPPORT, false)
                .with(HAS_POST, false)
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(EAST, false)
                .with(WEST, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_SUPPORT, HAS_POST);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean solidBelow = world.getBlockState(pos.down()).isFullCube(world, pos.down());
        BlockState updated = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        return updated.with(HAS_SUPPORT, solidBelow);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean solidBelow = ctx.getWorld().getBlockState(ctx.getBlockPos().down()).isFullCube(ctx.getWorld(), ctx.getBlockPos().down());
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(HAS_SUPPORT, solidBelow);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(ModItems.WRENCH)) {
            if (!world.isClient()) {
                boolean hasPost = state.get(FenceBlock.HAS_POST);
                world.setBlockState(pos, state.with(FenceBlock.HAS_POST, !hasPost));
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
                        SoundCategory.BLOCKS, 0.5F, hasPost ? 0.8F : 1.2F);
            }
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
