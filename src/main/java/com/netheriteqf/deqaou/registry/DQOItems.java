package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.Deqaou;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.netheriteqf.deqaou.registry.DQOItemGroup.DEQAOU_TAB;

public class DQOItems implements ModInitializer {
    public static Rarity SPIRITLY = Rarity.valueOf("SPIRITLY");
    public static Item EVIL_GOGGLES = new Item(new OwoItemSettings().rarity(SPIRITLY).group(DEQAOU_TAB));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(Deqaou.MODID, "evil_goggles"), EVIL_GOGGLES);
    }
}
