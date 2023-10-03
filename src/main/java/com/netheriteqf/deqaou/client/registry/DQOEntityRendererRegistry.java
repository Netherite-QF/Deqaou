package com.netheriteqf.deqaou.client.registry;

import com.netheriteqf.deqaou.client.entity.MagicalItemEntityRenderer;
import com.netheriteqf.deqaou.registry.DQOEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class DQOEntityRendererRegistry implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(DQOEntities.MAGICAL_ITEM, MagicalItemEntityRenderer::new);
    }
}
