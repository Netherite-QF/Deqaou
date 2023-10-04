package com.netheriteqf.deqaou.mixin;

import com.netheriteqf.deqaou.client.hud.ManaBarHud;
import com.netheriteqf.deqaou.item.StaffItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    @Final
    @Mutable
    private final MinecraftClient client;
    private int enteringY;

    protected InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V"))
    private void renderManaBar(DrawContext context, float tickDelta, CallbackInfo ci) {
        PlayerEntity player = client.player;
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        if (player != null && player.getMainHandStack().getItem() instanceof StaffItem) {
            for (int nowY = 0; nowY < 66; nowY =  nowY + 2) {
                enteringY = nowY;
            }
            ManaBarHud.renderMainBar(context, client, player);
        }
    }
}
