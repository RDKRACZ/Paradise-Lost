package net.id.aether.blocks.natural.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class AetherHangingMushroomPlantBlock extends AetherMushroomPlantBlock {
    public AetherHangingMushroomPlantBlock(Settings settings, TagKey<Block> plantableOn) {
        super(settings, plantableOn);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(5.0D, 10.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    }

    @Override
    protected boolean canPlantOnTop(BlockState ceil, BlockView world, BlockPos pos) {
        return ceil.isOpaqueFullCube(world, pos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isIn(plantableOn)) {
            return true;
        } else {
            return world.getBaseLightLevel(pos, 0) < 11 && this.canPlantOnTop(blockState, world, blockPos);
        }
    }
}
