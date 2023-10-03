package com.netheriteqf.deqaou.block.entity;

import com.google.common.collect.Lists;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.netheriteqf.deqaou.registry.DQOBlockEntity;
import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Stack;

public class CrucibleBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    public final DefaultedList<ItemStack> ITEMS = DefaultedList.ofSize(9, ItemStack.EMPTY);
    public final DefaultedList<FluidStack> FLUIDS = DefaultedList.ofSize(5, FluidStack.empty());
    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(DQOBlockEntity.OBSIDIAN_CRUIBLE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return DefaultedList.of();
    }

    @Override
    public int size() {
        return ImplementedInventory.super.size();
    }

    @Override
    public ItemStack getStack(int slot) {
        return ImplementedInventory.super.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        return ImplementedInventory.super.removeStack(slot, count);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);
    }

    @Override
    public void clear() {
        ImplementedInventory.super.clear();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[size()];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }
}
