package com.kotmatross.shaderfixer.mixins.late.OPENCOMPUTERS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import li.cil.oc.util.RenderState$;

@Mixin(value = RenderState$.class, priority = 999)
public class MixinRenderState {

    @Unique
    private static float shader_fixer$lbx;
    @Unique
    private static float shader_fixer$lby;

    @Inject(method = "disableLighting", at = @At(value = "HEAD"), remap = false)
    private void disableLight(CallbackInfo ci) {
        shader_fixer$lbx = Utils.GetLastBrightnessX();
        shader_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(method = "enableLighting", at = @At(value = "HEAD"), remap = false)
    private void enableLight(CallbackInfo ci) {
        Utils.DisableFullBrightness(shader_fixer$lbx, shader_fixer$lby);
    }
}
