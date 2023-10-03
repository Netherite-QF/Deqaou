package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.block.entity.CrucibleBlockEntity;
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

public class DQOBlockEntity implements BlockEntityRegistryContainer {
    public static final BlockEntityType<CrucibleBlockEntity> OBSIDIAN_CRUIBLE = FabricBlockEntityTypeBuilder.create(CrucibleBlockEntity::new, DQOBlocks.OBSIDIAN_CRUCIBLE).build();
}
