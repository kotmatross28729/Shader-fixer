package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderOverhead.class, priority = 999)
public class MixinRenderOverhead {

     @Unique private static float shaders_fixer$lbx;
     @Unique private static float shaders_fixer$lby;

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "HEAD"), remap = false)
    private static void drawTag(float offset, double distsq, String name, double x, double y, double z, int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "TAIL"), remap = false)
    private static void drawTag2(float offset, double distsq, String name, double x, double y, double z, int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }

    @Unique private static float shaders_fixer$lbx2;
    @Unique private static float shaders_fixer$lby2;

    @Inject(method = "renderThermalSight",
        at = @At(value = "HEAD"), remap = false)
    private static void renderThermalSight(float partialTicks, CallbackInfo ci) {
        shaders_fixer$lbx2 = Utils.GetLastBrightnessX();
        shaders_fixer$lby2 = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    @Inject(method = "renderThermalSight",
        at = @At(value = "TAIL"), remap = false)
    private static void renderThermalSight2(float partialTicks, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2, shaders_fixer$lby2);
    }

    @Inject(method = "renderMarkers",
        at = @At(value = "HEAD"), remap = false)
    private static void renderMarkers(float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }
}
