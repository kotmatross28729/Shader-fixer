package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderSparks;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

@Mixin(value = RenderSparks.class, priority = 999)
public class MixinRenderSparks {

    @WrapMethod(method = "renderSpark", remap = false)
    private static void dontCastShadow(int seed, double x, double y, double z, float length, int min, int max,
        int color1, int color2, Operation<Integer> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(seed, x, y, z, length, min, max, color1, color2);
        }
    }

    @Inject(method = "renderSpark", at = @At(value = "HEAD"), remap = false)
    private static void renderSpark(int seed, double x, double y, double z, float length, int min, int max, int color1,
        int color2, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderSpark", at = @At(value = "TAIL"), remap = false)
    private static void renderSpark2(int seed, double x, double y, double z, float length, int min, int max, int color1,
        int color2, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
