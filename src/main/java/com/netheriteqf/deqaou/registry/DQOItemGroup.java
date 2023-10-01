package com.netheriteqf.deqaou.registry;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DQOItemGroup implements ModInitializer {
    public static final OwoItemGroup DEQAOU_TAB = OwoItemGroup
            .builder(new Identifier("deqaou", "item_group"), () -> Icon.of(DQOItems.EVIL_GOGGLES))
            .build();

    @Override
    public void onInitialize() {

    }
}
