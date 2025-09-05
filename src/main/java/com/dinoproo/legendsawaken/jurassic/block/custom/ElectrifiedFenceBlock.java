package com.dinoproo.legendsawaken.jurassic.block.custom;

import com.dinoproo.legendsawaken.jurassic.block.JurassicBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElectrifiedFenceBlock extends FenceBlock {
    private final float fenceDamage;

    public static final BooleanProperty HAS_SIGN = BooleanProperty.of("has_sign");
    public static final DirectionProperty SIGN_FACING = DirectionProperty.of("sign_facing", Direction.Type.HORIZONTAL);

    public ElectrifiedFenceBlock(Settings settings, float fenceDamage) {
        super(settings);
        this.fenceDamage = fenceDamage;

        this.setDefaultState(this.getDefaultState()
                .with(HAS_SUPPORT, false)
                .with(HAS_POST, false)
                .with(HAS_SIGN, false)
                .with(SIGN_FACING, Direction.NORTH)
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(EAST, false)
                .with(WEST, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_SIGN, SIGN_FACING);
    }

    private static BooleanProperty FacingToProperty(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalArgumentException("Invalid horizontal direction: " + dir);
        };
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        entity.damage(world.getDamageSources().lightningBolt(), fenceDamage);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        super.onUseWithItem(stack, state, world, pos, player, hand, hit);

        Direction side = hit.getSide();

        if (stack.isOf(JurassicBlocks.TEN_K_VOLTS_SIGN.asItem()) && !state.get(HAS_SIGN)) {
            if (side.getAxis().isHorizontal() && !state.get(FacingToProperty(side))) {
                if (!world.isClient()) {
                    BlockState newState = state.with(HAS_SIGN, true).with(SIGN_FACING, side);
                    world.setBlockState(pos, newState, 3);
                    world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS);
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                }
                return ItemActionResult.SUCCESS;
            }
        }

        if (state.get(HAS_SIGN) && stack.isEmpty()) {
            if (side == state.get(SIGN_FACING)) {
                if (!world.isClient) {
                    world.setBlockState(pos, state.with(HAS_SIGN, false), 3);
                    player.giveItemStack(new ItemStack(JurassicBlocks.TEN_K_VOLTS_SIGN));
                }
                return ItemActionResult.SUCCESS;
            }
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);

        if (!world.isClient && state.get(HAS_SIGN)) {
            dropStack(world, pos, new ItemStack(JurassicBlocks.TEN_K_VOLTS_SIGN));
        }
    }
}
