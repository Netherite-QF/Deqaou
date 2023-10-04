package com.netheriteqf.deqaou.entity;

import com.netheriteqf.deqaou.item.MagicalItem;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class MagicalItemEntity extends ItemEntity implements Ownable {
    public UUID ownerUuid;
    public LivingEntity owner;
    public float moveTime;
    public float windUp;

    public MagicalItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicalItemEntity(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        age++;
        if (age > MagicalItem.maxAge()) {
            getWorld().createExplosion(this, getX(), getY(), getZ(), 20f, World.ExplosionSourceType.BLOCK);
        }
        else {
            if (new Random().nextBoolean()) move();
        }
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
        float velocity = windUp < 0.2f ? 0 : Math.min(windUp-0.22f, 0.3f)*5f;
        moveTime++;
        Vec3d desiredVel = desiredLocation.subtract(getPos()).normalize().multiply(velocity, velocity, velocity);
        float xMotion = (float) ((float) MathHelper.lerp(0.01, getVelocity().x, desiredVel.x) + new Random().nextFloat() + (new Random().nextFloat() / getX()));
        float yMotion = (float) ((float) MathHelper.lerp(0.03, getVelocity().y, desiredVel.y) + new Random().nextFloat() + (new Random().nextFloat() / getY()));
        float zMotion = (float) ((float) MathHelper.lerp(0.02, getVelocity().z, desiredVel.z) + new Random().nextFloat() + (new Random().nextFloat() / getZ()));
        Vec3d resultingMotion = new Vec3d(xMotion, yMotion, zMotion);
        setVelocity(resultingMotion);

        if (distance < 0.2f && new Random().nextBoolean()) {
            if (owner instanceof PlayerEntity player && isAlive()) {
                player.giveItemStack(this.getStack());
                if (player.getHealth() < ((player.getHealth() + 5) / 3)) {
                    getWorld().createExplosion(this, getX(), getY(), getZ(), 10f, World.ExplosionSourceType.MOB);
                }
            } else {
                ItemEntity itemEntity = new ItemEntity(owner.getWorld(), owner.getX(), owner.getY() + 0.5, owner.getZ(), this.getStack());
                itemEntity.setPickupDelay(40);
                itemEntity.setVelocity(itemEntity.getVelocity().multiply(0, 1, 0));
                owner.getWorld().spawnEntity(itemEntity);
            }
            remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("age", age);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        age = nbt.getInt("age");
    }
}
