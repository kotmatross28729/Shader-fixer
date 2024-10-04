package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderOverhead.class, priority = 999)
public class MixinRenderOverhead {

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "HEAD"), remap = false)
    private static void drawTag(float offset, double distsq, String name, double x, double y, double z, int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    @Inject(method = "renderThermalSight",
        at = @At(value = "HEAD"), remap = false)
    private static void renderThermalSight(float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    @Inject(method = "renderMarkers",
        at = @At(value = "HEAD"), remap = false)
    private static void renderMarkers(float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }
}
