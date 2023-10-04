package com.netheriteqf.deqaou.mixin;

import com.netheriteqf.deqaou.entity.MagicalItemEntity;
import com.netheriteqf.deqaou.item.MagicalItem;
import com.netheriteqf.deqaou.registry.DQOEntities;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getStack();

    @Inject(method = "tick", at = @At("HEAD"))
    private void changeInto(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (itemEntity.getStack().getItem() instanceof MagicalItem && !(itemEntity instanceof MagicalItemEntity)) {
            itemEntity.discard();
            MagicalItemEntity newItem = new MagicalItemEntity(itemEntity.getWorld(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemEntity.getStack());
            newItem.setToDefaultPickupDelay();
            newItem.move();
            itemEntity.getWorld().spawnEntity(newItem);
        }
    }
}
