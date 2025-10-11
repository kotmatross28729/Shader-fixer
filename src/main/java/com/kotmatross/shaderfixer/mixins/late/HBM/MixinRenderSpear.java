package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderSpear;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderSpear.class, priority = 999)
public class MixinRenderSpear {

    @ModifyArg(
        method = "renderFlash",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 10F;
    }

    @Inject(method = "renderFlash", at = @At(value = "HEAD"), remap = false)
    private void renderFlash(double intensity, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderFlash", at = @At(value = "TAIL"), remap = false)
    private void renderFlash2(double intensity, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
