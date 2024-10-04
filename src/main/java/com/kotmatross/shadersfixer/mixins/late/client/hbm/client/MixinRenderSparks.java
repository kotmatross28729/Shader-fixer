package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.util.RenderSparks;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSparks.class, priority = 999)
public class MixinRenderSparks {
     @Unique
     private static float shaders_fixer$lbx;
     @Unique
     private static float shaders_fixer$lby;

    @Inject(method = "renderSpark",
        at = @At(value = "HEAD"), remap = false)
    private static void renderSpark(int seed, double x, double y, double z, float length, int min, int max, int color1, int color2, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderSpark",
        at = @At(value = "TAIL"), remap = false)
    private static void renderSpark2(int seed, double x, double y, double z, float length, int min, int max, int color1, int color2, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }
}
