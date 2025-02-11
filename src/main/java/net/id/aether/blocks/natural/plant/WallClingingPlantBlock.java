package net.id.aether.blocks.natural.plant;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallClingingPlantBlock extends PlantBlock {

    protected static final Map<Direction, VoxelShape> SHAPES;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private final @Nullable TagKey<Block> clingableBlocks;

    public WallClingingPlantBlock(Settings settings, @Nullable TagKey<Block> clingableBlocks) {
        super(settings);
        this.clingableBlocks = clingableBlocks;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        var wall = pos.offset(state.get(FACING));
        return canClingTo(world.getBlockState(wall));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var side = ctx.getSide().getOpposite();
        if(side.getHorizontal() >= 0) {
            return getDefaultState().with(FACING, ctx.getSide().getOpposite());
        }
        return null;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return true;
    }

    public boolean canClingTo(BlockState state) {
        return clingableBlocks == null || state.isIn(clingableBlocks);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    static {
        var builder = ImmutableMap.<Direction, VoxelShape>builder();
        builder.put(Direction.NORTH, Block.createCuboidShape(0, 4, 0, 16, 12, 6));
        builder.put(Direction.EAST, Block.createCuboidShape(10, 4, 0, 16, 12, 16));
        builder.put(Direction.SOUTH, Block.createCuboidShape(0, 4, 10, 16, 12, 16));
        builder.put(Direction.WEST, Block.createCuboidShape(0, 4, 0, 6, 12, 16));
        SHAPES = builder.build();
    }
}
