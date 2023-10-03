package com.netheriteqf.deqaou.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;

@Environment(EnvType.CLIENT)
public class MagicalItemEntityRenderer extends ItemEntityRenderer {
    public MagicalItemEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(itemEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
