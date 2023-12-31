package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.Deqaou;
import com.netheriteqf.deqaou.item.*;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;


public class DQOItems implements ItemRegistryContainer {
    public static Rarity SPIRITLY = Rarity.valueOf("SPIRITLY");
    public static final Item EVIL_GOGGLES = new EvilGogglesItem(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY).maxCount(1));
    public static final Item SWEET_GLOVE = new SweetGolveItem(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY).maxCount(1));
    public static final Item EMOTION_CRYSTAL = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item POSOUENV_GUIDE = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item ANGERIUM = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item HAPOUNDIUM = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item PITOCKIUM = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item SADANTIUM = new Item(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item PRIMARY_CANE = new StaffItem(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(Rarity.RARE));
    public static final Item CALMIUM = new MagicalItem(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
    public static final Item ELEMENT_BELT = new ElementBeltItem(new OwoItemSettings().group(Deqaou.DEQAOU_TAB).rarity(SPIRITLY));
}
