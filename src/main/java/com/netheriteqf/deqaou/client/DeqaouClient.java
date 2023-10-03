package com.netheriteqf.deqaou.client;

import com.netheriteqf.deqaou.client.registry.DQOEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class DeqaouClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        new DQOEntityRendererRegistry().onInitializeClient();
    }
}
