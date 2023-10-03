package com.netheriteqf.deqaou;

import com.netheriteqf.deqaou.registry.*;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Deqaou implements ModInitializer {
    public static final String MODID = "deqaou";

    public static final OwoItemGroup DEQAOU_TAB = OwoItemGroup
            .builder(new Identifier("deqaou", "deqaou_tab"), () -> Icon.of(DQOItems.EVIL_GOGGLES))
            .initializer(group -> {

            })
            .build();

    @Override
    public void onInitialize() {
        DEQAOU_TAB.initialize();
        new DQORecipeTypes().onInitialize();
        FieldRegistrationHandler.register(DQOItems.class, MODID, false);
        FieldRegistrationHandler.register(DQOBlocks.class, MODID, false);
        FieldRegistrationHandler.register(DQOBlockEntity.class, MODID, false);
    }
}
