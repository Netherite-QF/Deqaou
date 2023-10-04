package com.netheriteqf.deqaou.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.netheriteqf.deqaou.Deqaou;
import com.netheriteqf.deqaou.item.StaffItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ManaBarHud {
    protected static final Identifier MANA_BAR_TEXTURE = new Identifier("deqaou:textures/gui/mana_bar.png");

    public static void renderMainBar(DrawContext context, MinecraftClient client, PlayerEntity player) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        context.drawTexture(MANA_BAR_TEXTURE, 0, 0, 0, 0, 256, 62);
    }
}
