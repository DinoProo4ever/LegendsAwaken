package com.dinoproo.legendsawaken.jurassic.block.custom;

import com.dinoproo.legendsawaken.jurassic.block.entity.custom.FenceGateBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FenceGateBlock extends Block implements BlockEntityProvider {
    private static final VoxelShape LEFT_POST =
            Block.createCuboidShape(-4, 0, 3, 6, 16, 13);
    private static final VoxelShape LEFT_POST_TOP =
            Block.createCuboidShape(-4, 0, 3, 6, 17, 13);

    private static final VoxelShape COVER =
            Block.createCuboidShape(0, 0, 4, 16, 16, 12);
    private static final VoxelShape COVER_TOP =
            Block.createCuboidShape(0, 0, 4, 16, 14, 12);

    private static final VoxelShape GATE_COVER =
            Block.createCuboidShape(0, 0, 4, 6, 16, 12);
    private static final VoxelShape GATE_COVER_TOP =
            Block.createCuboidShape(0, 0, 4, 6, 14, 12);

    private static final VoxelShape GATE_OPEN =
            Block.createCuboidShape(0, 0, 6, 10, 16, 10);
    private static final VoxelShape GATE_OPEN_TOP =
            Block.createCuboidShape(0, 0, 6, 10, 12, 10);

    private static final VoxelShape GATE_CLOSED =
            Block.createCuboidShape(0, 0, 6, 16, 16, 10);
    private static final VoxelShape GATE_CLOSED_TOP =
            Block.createCuboidShape(0, 0, 6, 16, 12, 10);

    private static final VoxelShape RIGHT_POST =
            Block.createCuboidShape(10, 0, 3, 20, 16, 13);
    private static final VoxelShape RIGHT_POST_TOP =
            Block.createCuboidShape(10, 0, 3, 20, 17, 13);

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final EnumProperty<GatePart> PART = EnumProperty.of("part", GatePart.class);

    private static final Map<GatePart, BlockPos> RELATIVE_POS = new HashMap<>();
    static {
        RELATIVE_POS.put(GatePart.X0_Y0, new BlockPos(-2, 0, 0));
        RELATIVE_POS.put(GatePart.X1_Y0, new BlockPos(-1, 0, 0));
        RELATIVE_POS.put(GatePart.X2_Y0, BlockPos.ORIGIN);
        RELATIVE_POS.put(GatePart.X3_Y0, new BlockPos(1, 0, 0));
        RELATIVE_POS.put(GatePart.X4_Y0, new BlockPos(2, 0, 0));

        RELATIVE_POS.put(GatePart.X0_Y1, new BlockPos(-2, 1, 0));
        RELATIVE_POS.put(GatePart.X1_Y1, new BlockPos(-1, 1, 0));
        RELATIVE_POS.put(GatePart.X2_Y1, new BlockPos(0, 1, 0));
        RELATIVE_POS.put(GatePart.X3_Y1, new BlockPos(1, 1, 0));
        RELATIVE_POS.put(GatePart.X4_Y1, new BlockPos(2, 1, 0));

        RELATIVE_POS.put(GatePart.X0_Y2, new BlockPos(-2, 2, 0));
        RELATIVE_POS.put(GatePart.X1_Y2, new BlockPos(-1, 2, 0));
        RELATIVE_POS.put(GatePart.X2_Y2, new BlockPos(0, 2, 0));
        RELATIVE_POS.put(GatePart.X3_Y2, new BlockPos(1, 2, 0));
        RELATIVE_POS.put(GatePart.X4_Y2, new BlockPos(2, 2, 0));
    }

    public FenceGateBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(OPEN, false)
                .with(PART, GatePart.X2_Y0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, OPEN, PART);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (state.get(PART) == GatePart.X2_Y0) {
            return new FenceGateBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        GatePart part = state.get(PART);
        boolean open = state.get(OPEN);
        Direction facing = state.get(FACING);

        VoxelShape baseShape = VoxelShapes.empty();

        if (part == GatePart.X0_Y0 || part == GatePart.X0_Y1) {
            baseShape = VoxelShapes.union(LEFT_POST, COVER);
        } else if (part == GatePart.X0_Y2) {
            baseShape = VoxelShapes.union(LEFT_POST_TOP, COVER_TOP);
        } else if (part == GatePart.X1_Y0 || part == GatePart.X1_Y1) {
            baseShape = COVER;
        } else if (part == GatePart.X1_Y2) {
            baseShape = COVER_TOP;
        } else if (open) {
            if (part == GatePart.X2_Y0 || part == GatePart.X2_Y1) {
                baseShape = VoxelShapes.union(GATE_COVER, GATE_OPEN);
            } else if (part == GatePart.X2_Y2) {
                baseShape = VoxelShapes.union(GATE_COVER_TOP, GATE_OPEN_TOP);
            } else if (part == GatePart.X4_Y0 || part == GatePart.X4_Y1) {
                baseShape = RIGHT_POST;
            } else if (part ==  GatePart.X4_Y2) {
                baseShape = RIGHT_POST_TOP;
            }
        } else {
            if (part == GatePart.X2_Y0 || part == GatePart.X2_Y1) {
                baseShape = VoxelShapes.union(GATE_COVER, GATE_CLOSED);
            } else if (part == GatePart.X2_Y2) {
                baseShape = VoxelShapes.union(GATE_COVER_TOP,GATE_CLOSED_TOP);
            } else if (part == GatePart.X3_Y0 || part == GatePart.X3_Y1) {
                baseShape = GATE_CLOSED;
            } else if (part == GatePart.X3_Y2) {
                baseShape = GATE_CLOSED_TOP;
            } else if (part == GatePart.X4_Y0 || part == GatePart.X4_Y1) {
                baseShape = VoxelShapes.union(RIGHT_POST, GATE_CLOSED);
            } else if (part ==  GatePart.X4_Y2) {
                baseShape = VoxelShapes.union(RIGHT_POST_TOP, GATE_CLOSED_TOP);
            }
        }
        return rotateShape(baseShape, Direction.NORTH, facing);
    }

    public static VoxelShape rotateShape(VoxelShape shape, Direction from, Direction to) {
        int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1],
                    VoxelShapes.cuboid(
                            1 - maxZ, minY, minX,
                            1 - minZ, maxY, maxX
                    )));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(PART, GatePart.X2_Y0);

        if (!canPlaceAt(state, ctx.getWorld(), ctx.getBlockPos())) {
            return null;
        }

        return state;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(FACING);

        for (Map.Entry<GatePart, BlockPos> entry : RELATIVE_POS.entrySet()) {
            BlockPos target = pos.add(rotate(entry.getValue(), facing));
            BlockState targetState = world.getBlockState(target);

            if (!targetState.isAir()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            Direction facing = state.get(FACING);

            for (Map.Entry<GatePart, BlockPos> entry : RELATIVE_POS.entrySet()) {
                GatePart part = entry.getKey();
                BlockPos offset = rotate(entry.getValue(), facing);
                BlockPos target = pos.add(offset);

                world.setBlockState(target, this.getDefaultState()
                        .with(FACING, facing)
                        .with(PART, part));
            }
        }
    }

    private BlockPos rotate(BlockPos offset, Direction facing) {
        int x = offset.getX();
        int y = offset.getY();
        int z = offset.getZ();

        return switch (facing) {
            case NORTH -> new BlockPos(x, y, z);
            case SOUTH -> new BlockPos(-x, y, -z);
            case WEST -> new BlockPos(z, y, -x);
            case EAST -> new BlockPos(-z, y, x);
            default -> offset;
        };
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            BlockPos centralPos = getCentralPos(world, pos, state);
            Direction facing = state.get(FACING);

            for (Map.Entry<GatePart, BlockPos> entry : RELATIVE_POS.entrySet()) {
                BlockPos target = centralPos.add(rotate(entry.getValue(), facing));
                BlockState targetState = world.getBlockState(target);
                if (targetState.getBlock() == this) {
                    world.breakBlock(target, true, player);
                }
            }
        }

        return super.onBreak(world, pos, state, player);
    }

    private BlockPos getCentralPos(World world, BlockPos brokenPos, BlockState state) {
        Direction facing = state.get(FACING);

        for (Map.Entry<GatePart, BlockPos> entry : RELATIVE_POS.entrySet()) {
            BlockPos rotatedOffset = rotate(entry.getValue(), facing);
            BlockPos candidate = brokenPos.subtract(rotatedOffset);

            BlockState candidateState = world.getBlockState(candidate);
            if (candidateState.getBlock() == this && candidateState.get(PART) == GatePart.X2_Y0) {
                return candidate;
            }
        }

        return brokenPos;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            boolean newOpen = !state.get(OPEN);

            BlockPos origin = findOrigin(pos, state.get(PART));

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 3; y++) {
                    BlockPos target = origin.add(x, y, 0);
                    BlockState targetState = world.getBlockState(target);
                    if (targetState.getBlock() instanceof FenceGateBlock) {
                        world.setBlockState(target, targetState.with(OPEN, newOpen), 3);
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    private BlockPos findOrigin(BlockPos pos, GatePart part) {
        int index = part.ordinal();
        int x = index % 5;
        int y = index / 5;
        return pos.add(-x, -y, 0);
    }

    public enum GatePart implements StringIdentifiable {
        X0_Y0("x0_y0"), X1_Y0("x1_y0"), X2_Y0("x2_y0"), X3_Y0("x3_y0"), X4_Y0("x4_y0"),
        X0_Y1("x0_y1"), X1_Y1("x1_y1"), X2_Y1("x2_y1"), X3_Y1("x3_y1"), X4_Y1("x4_y1"),
        X0_Y2("x0_y2"), X1_Y2("x1_y2"), X2_Y2("x2_y2"), X3_Y2("x3_y2"), X4_Y2("x4_y2");

        private final String name;
        GatePart(String name) { this.name = name; }
        @Override public String asString() { return this.name; }
    }
}
