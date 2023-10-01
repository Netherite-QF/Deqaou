package com.netheriteqf.deqaou.mixin;

import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(Formatting.class)
public class FormattingMixin {
    @Shadow
    @Mutable
    private static Formatting[] field_1072;

    FormattingMixin(String name, int ordinal, String inputName, char code, int color, Integer value) {
        throw new RuntimeException("I really wanna a register for that");
    }

    @Inject(method = "<clinit>()V", at = @At(value = "FIELD", shift = At.Shift.AFTER, target = "Lnet/minecraft/util/Formatting;field_1072:[Lnet/minecraft/util/Formatting;"))
    private static void customColor(CallbackInfo ci) {
        int ordinal = field_1072.length;
        field_1072 = Arrays.copyOf(field_1072, ordinal + 1);
        field_1072[ordinal] = (Formatting)(Object) new FormattingMixin("LITE_BLUE", ordinal, "LITE_BLUE",'丁', 211911, 12372479);
    }
}
