package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(targets = "com.hbm.render.item.ItemRenderLibrary$10")
public class MixinItemRenderLibrary {

    // Pure fucking bytecode reading simulator
    @Inject(
        method = "renderCommon",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void injectBeforeRenderPart(CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "renderCommon",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    private void injectAfterRenderPart(CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
