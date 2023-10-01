package com.netheriteqf.deqaou;

import com.netheriteqf.deqaou.registry.DQOItems;
import net.fabricmc.api.ModInitializer;

public class Deqaou implements ModInitializer {
    public static final String MODID = "deqaou";

    @Override
    public void onInitialize() {
        new DQOItems().onInitialize();
    }
}
