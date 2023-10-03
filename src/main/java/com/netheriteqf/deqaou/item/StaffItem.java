package com.netheriteqf.deqaou.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class StaffItem extends Item {
    public StaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player) {
            Item holdingItem = player.getStackInHand(Hand.MAIN_HAND).getItem();
            if (holdingItem instanceof StaffItem) {
                //TODO render Mana Bar HUD
            }
        }
    }
}
