package com.netheriteqf.deqaou.block;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.netheriteqf.deqaou.block.entity.CrucibleBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Stack;

public class CrucibleBlock extends Block implements BlockEntityProvider {
//    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public CrucibleBlock(Settings settings) {
        super(settings);
//        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrucibleBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient) {
            if (!(world.getBlockEntity(pos) instanceof CrucibleBlockEntity crucible))
                return ActionResult.PASS;
            if (hand != Hand.OFF_HAND && !(player.getHandItems() instanceof BucketItem)) {
                if (!player.getMainHandStack().isEmpty() && player.isSneaking()) {
                    player.dropItem(crucible.getStack(crucible.size()).getItem());
                    crucible.markDirty();
                    return ActionResult.CONSUME;
                } else if (!player.getMainHandStack().isEmpty() && !player.isSneaking()) {
                    player.getMainHandStack().decrement(1);
                    crucible.setStack(, player.getMainHandStack());
                    crucible.markDirty();
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.PASS;
                }
            } else {
                if (hand != Hand.OFF_HAND) {
                    BucketItem bucket = (BucketItem) player.getHandItems();
                    FluidStack fluid = ;
                    if (bucket.fluid == Fluids.EMPTY) {
                        player.getMainHandStack().decrement(1);
                        player.dropItem(fluid.getFluid().getBucketItem());
                        crucible.markDirty();
                        return ActionResult.CONSUME;
                    } else {
                        player.getMainHandStack().decrement(1);
                        player.dropItem(Items.BUCKET);
                        crucible.markDirty();
                        return ActionResult.CONSUME;
                    }
                }
            }
        } else {
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return VoxelShapes.cuboid(0, 0, 0, 16, 14.0 / 16, 16);
    }
}
