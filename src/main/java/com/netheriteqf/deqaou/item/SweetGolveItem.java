package com.netheriteqf.deqaou.item;

import com.netheriteqf.deqaou.client.model.EvilGogglesModel;
import com.netheriteqf.deqaou.client.model.SweetGloveModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

public class SweetGolveItem extends TrinketItem implements TrinketRenderer {
    public SweetGolveItem(Settings settings) {
        super(settings);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        SweetGloveModel model  = new SweetGloveModel(SweetGloveModel.getTexturedModelData().createModel());
        VertexConsumer vertex = ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(new Identifier("deqaou", "textures/entity/sweet_glove.png")), false, stack.hasGlint());
        if (contextModel instanceof BipedEntityModel<? extends LivingEntity> biped) {
            matrices.push();
            if (Objects.equals(slotReference.inventory().getSlotType().getGroup(), "hand")) {
                biped.rightArm.rotate(matrices);
                matrices.translate(-0.035,  -0.8, 0);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0F));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0F));
            } else {
                biped.leftArm.rotate(matrices);
                matrices.translate(0.035,  -0.8, 0);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0F));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));
            }
            model.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
            matrices.pop();
        }
    }
}
