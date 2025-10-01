package com.kotmatross.shaderfixer.mixins.late.SIGNPIC;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kamesuta.mc.signpic.render.RenderHelper;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawLoadingCircle", at = @At(value = "HEAD"), remap = false)
    private static void drawLoadingCircle(int msPerRoundInner, int msPerRoundOuter, CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(method = "drawDesignCircle", at = @At(value = "HEAD"), remap = false)
    private static void drawDesignCircle(CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(method = "drawProgressCircle(F)V", at = @At(value = "HEAD"), remap = false)
    private static void drawProgressCircle(float progress, CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(method = "drawProgressCircle(IF)V", at = @At(value = "HEAD"), remap = false)
    private static void drawProgressCircle(int mode, float r, CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(method = "drawRect(IFFFF)V", at = @At(value = "HEAD"), remap = false)
    private static void drawRect(int mode, float x1, float y1, float x2, float y2, CallbackInfo ci) {
        Utils.fix();
    }
}
