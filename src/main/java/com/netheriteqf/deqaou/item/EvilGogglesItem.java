package com.netheriteqf.deqaou.item;

import com.netheriteqf.deqaou.client.model.EvilGogglesModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class EvilGogglesItem extends TrinketItem implements TrinketRenderer {
    public EvilGogglesItem(Settings settings) {
        super(settings);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        EvilGogglesModel model  = new EvilGogglesModel(EvilGogglesModel.getTexturedModelData().createModel());
        VertexConsumer vertex = ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(new Identifier("deqaou", "textures/entity/evil_goggles.png")), false, stack.hasGlint());
        if (contextModel instanceof BipedEntityModel<? extends LivingEntity> biped) {
            matrices.push();
            biped.head.rotate(matrices);
            matrices.translate(0,  -1.7, 0);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0F));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0F));
            model.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
            matrices.pop();
        }
    }
}
