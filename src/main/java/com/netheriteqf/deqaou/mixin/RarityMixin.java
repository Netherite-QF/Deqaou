package com.netheriteqf.deqaou.mixin;

import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(Rarity.class)
public class RarityMixin {
    @Shadow
    @Mutable
    private static Rarity[] field_8905;

    public RarityMixin(String name, int ordinal, Formatting color) {
        throw new RuntimeException("I wanna a RarityRegister in fact");
    }

    @Inject(method = "<clinit>()V", at = @At(value = "FIELD", shift = At.Shift.AFTER, target = "Lnet/minecraft/util/Rarity;field_8905:[Lnet/minecraft/util/Rarity;"))
    private static void customRarity(CallbackInfo ci) {
        int ordinal = field_8905.length;
        field_8905 = Arrays.copyOf(field_8905, ordinal + 1);
        field_8905[ordinal] = (Rarity)(Object) new RarityMixin("SPIRITLY", ordinal, Formatting.byName("LITE_BLUE"));
    }
}
