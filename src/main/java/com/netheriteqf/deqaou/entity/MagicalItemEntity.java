package com.netheriteqf.deqaou.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.UUID;

public class MagicalItemEntity extends ItemEntity implements Ownable {
    public UUID ownerUuid;
    public LivingEntity owner;
    public float moveTime;
    public float windUp;

    public MagicalItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        this.move();
    }

    public void move() {
        float friction = 0.94f;
        setVelocity(getVelocity().multiply(friction, friction, friction));
        if (owner == null || !owner.isAlive()) {
            if (this.getWorld().getTime() % 40L == 0) {
                PlayerEntity playerEntity = this.getWorld().getClosestPlayer(this, 10);
                if (playerEntity != null) {
                    setOwner(playerEntity.getUuid());
                }
            }
            return;
        }
        Vec3d desiredLocation = owner.getPos().add(0, owner.getHeight() / 3, 0);
        float distance = (float) squaredDistanceTo(desiredLocation);
        float velocity = windUp < 0.2f ? 0 : Math.min(windUp-0.2f, 0.35f)*5f;
        moveTime++;
        Vec3d desiredVel = desiredLocation.subtract(getPos()).normalize().multiply(velocity, velocity, velocity);
        float easing = 0.01f;
        float xMotion = (float) MathHelper.lerp(easing, getVelocity().x, desiredVel.x);
        float yMotion = (float) MathHelper.lerp(easing, getVelocity().y, desiredVel.y);
        float zMotion = (float) MathHelper.lerp(easing, getVelocity().z, desiredVel.z);
        Vec3d resultingMotion = new Vec3d(xMotion, yMotion, zMotion);
        setVelocity(resultingMotion);

        if (distance < 0.4f) {
            if (isAlive()) {
                if (owner instanceof PlayerEntity) {
                    ((PlayerEntity) owner).giveItemStack(this.getStack());
                } else {
                    ItemEntity itemEntity = new ItemEntity(owner.getWorld(), owner.getX(), owner.getY() + 0.5, owner.getZ(), this.getStack());
                    itemEntity.setPickupDelay(40);
                    itemEntity.setVelocity(itemEntity.getVelocity().multiply(0, 1, 0));
                    owner.getWorld().spawnEntity(itemEntity);
                }
            }
            remove(RemovalReason.DISCARDED);
        }
    }
}
