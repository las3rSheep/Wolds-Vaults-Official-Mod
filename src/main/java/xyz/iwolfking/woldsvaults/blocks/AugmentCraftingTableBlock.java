package xyz.iwolfking.woldsvaults.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import xyz.iwolfking.woldsvaults.blocks.tiles.AugmentCraftingTableTileEntity;
import xyz.iwolfking.woldsvaults.init.ModBlocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class AugmentCraftingTableBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING;

    public AugmentCraftingTableBlock() {
        super(Properties.of(Material.STONE).strength(1.5F, 6.0F).noOcclusion());
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return LecternBlock.SHAPE_COMMON;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return LecternBlock.SHAPE_COLLISION;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch ((Direction)state.getValue(FACING)) {
            case NORTH -> var10000 = LecternBlock.SHAPE_NORTH;
            case SOUTH -> var10000 = LecternBlock.SHAPE_SOUTH;
            case EAST -> var10000 = LecternBlock.SHAPE_EAST;
            case WEST -> var10000 = LecternBlock.SHAPE_WEST;
            default -> var10000 = LecternBlock.SHAPE_COMMON;
        }

        return var10000;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity var8 = world.getBlockEntity(pos);
            if (var8 instanceof AugmentCraftingTableTileEntity) {
                AugmentCraftingTableTileEntity entity = (AugmentCraftingTableTileEntity)var8;
                if (player instanceof ServerPlayer) {
                    ServerPlayer serverPlayer = (ServerPlayer)player;
                    NetworkHooks.openGui(serverPlayer, entity, (buffer) -> {
                        buffer.writeBlockPos(pos);
                    });
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.SUCCESS;
                }
            } else {
                return InteractionResult.SUCCESS;
            }
        }
    }

    public void onRemove(BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity var7 = world.getBlockEntity(pos);
            if (var7 instanceof AugmentCraftingTableTileEntity) {
                AugmentCraftingTableTileEntity entity = (AugmentCraftingTableTileEntity)var7;
                entity.getInventory().getOverSizedContents().forEach((stack) -> {
                    stack.splitByStackSize().forEach((split) -> {
                        Containers.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), split);
                    });
                });
                Containers.dropContents(world, pos, entity.getResultContainer());
                entity.getInventory().m_6211_();
                entity.getResultContainer().clearContent();
                world.updateNeighbourForOutputSignal(pos, this);
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @ParametersAreNonnullByDefault
    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return (BlockState)state.setValue(FACING, rot.rotate((Direction)state.getValue(FACING)));
    }

    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation((Direction)state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlocks.AUGMENT_CRAFTING_TABLE_ENTITY.create(pos, state);
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }
}
