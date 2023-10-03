package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.entity.MagicalItemEntity;
import io.wispforest.owo.registration.reflect.EntityRegistryContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.netheriteqf.deqaou.Deqaou.MODID;

public class DQOEntities implements EntityRegistryContainer {
    public static final EntityType<MagicalItemEntity> MAGICAL_ITEM = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MODID, "magical_item"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MagicalItemEntity::new)
                    .fireImmune()
                    .build()
    );
}
