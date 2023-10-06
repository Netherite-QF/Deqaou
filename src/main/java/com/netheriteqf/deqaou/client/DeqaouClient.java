package com.netheriteqf.deqaou.client;

import com.netheriteqf.deqaou.client.registry.DQOEntityRendererRegistry;
import com.netheriteqf.deqaou.registry.DQOItems;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.item.Item;

public class DeqaouClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        new DQOEntityRendererRegistry().onInitializeClient();
        registerRenderedTrinket(DQOItems.EVIL_GOGGLES);
        registerRenderedTrinket(DQOItems.SWEET_GLOVE);
    }

    private void registerRenderedTrinket(Item trinket) {
        TrinketRendererRegistry.registerRenderer(trinket, (TrinketRenderer) trinket);
    }
}
