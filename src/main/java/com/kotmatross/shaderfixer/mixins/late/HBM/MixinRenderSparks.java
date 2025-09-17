package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderSparks;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = RenderSparks.class, priority = 999)
public class MixinRenderSparks {

    @Inject(method = "renderSpark", at = @At(value = "HEAD"), remap = false)
    private static void renderSpark(int seed, double x, double y, double z, float length, int min, int max, int color1,
        int color2, CallbackInfo ci, @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        shader_fixer$lbx.set(Utils.GetLastBrightnessX());
        shader_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderSpark", at = @At(value = "TAIL"), remap = false)
    private static void renderSpark2(int seed, double x, double y, double z, float length, int min, int max, int color1,
        int color2, CallbackInfo ci, @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        Utils.DisableFullBrightness(shader_fixer$lbx.get(), shader_fixer$lby.get());
    }
}
