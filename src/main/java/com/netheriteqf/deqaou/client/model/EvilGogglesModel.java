package com.netheriteqf.deqaou.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class EvilGogglesModel  extends EntityModel<Entity> {
    private final ModelPart main;
    private final ModelPart bb_main;
    public EvilGogglesModel(ModelPart root) {
        this.main = root.getChild("main");
        this.bb_main = root.getChild("bb_main");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(14, 11).cuboid(-5.0F, -4.0F, -2.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(12, 18).cuboid(-4.0F, -3.0F, -2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(1.0F, -4.0F, -2.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(11, 11).cuboid(2.0F, -3.0F, -2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.0F, -3.0F, 1.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F))
                .uv(6, 18).cuboid(-5.0F, -3.0F, 1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(4.0F, -3.0F, 1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, -4.0F));

        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 5).cuboid(-2.0F, -4.0F, 5.0F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
