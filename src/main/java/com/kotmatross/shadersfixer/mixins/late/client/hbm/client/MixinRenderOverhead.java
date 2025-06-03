package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = RenderOverhead.class, priority = 999)
public class MixinRenderOverhead {

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "HEAD"), remap = false)
    private static void drawTag(float offset, double distsq, String name, double x, double y, double z, int dist,
        boolean depthTest, int color, int shadowColor, CallbackInfo ci,
        @Share("shaders_fixer$lbx") LocalFloatRef shaders_fixer$lbx,
        @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
        shaders_fixer$lbx.set(Utils.GetLastBrightnessX());
        shaders_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "TAIL"), remap = false)
    private static void drawTag2(float offset, double distsq, String name, double x, double y, double z, int dist,
        boolean depthTest, int color, int shadowColor, CallbackInfo ci,
        @Share("shaders_fixer$lbx") LocalFloatRef shaders_fixer$lbx,
        @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
        Utils.DisableFullBrightness(shaders_fixer$lbx.get(), shaders_fixer$lby.get());
    }

    @Inject(method = "renderThermalSight", at = @At(value = "HEAD"), remap = false)
    private static void renderThermalSight(float partialTicks, CallbackInfo ci,
        @Share("shaders_fixer$lbx2") LocalFloatRef shaders_fixer$lbx2,
        @Share("shaders_fixer$lby2") LocalFloatRef shaders_fixer$lby2) {
        shaders_fixer$lbx2.set(Utils.GetLastBrightnessX());
        shaders_fixer$lby2.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderThermalSight", at = @At(value = "TAIL"), remap = false)
    private static void renderThermalSight2(float partialTicks, CallbackInfo ci,
        @Share("shaders_fixer$lbx2") LocalFloatRef shaders_fixer$lbx2,
        @Share("shaders_fixer$lby2") LocalFloatRef shaders_fixer$lby2) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2.get(), shaders_fixer$lby2.get());
    }

    @Inject(method = "renderMarkers", at = @At(value = "HEAD"), remap = false)
    private static void renderMarkers(float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }

    /**
     * Fixes GL state leak with angelica
     */

    // Save GL states before rendering
    @Inject(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", shift = At.Shift.AFTER),
        remap = false)
    private static void shaders_fixer$AttribPush(float offset, double distsq, String name, double x, double y, double z,
        int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
    }

    // Release states
    @Inject(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.BEFORE),
        remap = false)
    private static void shaders_fixer$AttribPop(float offset, double distsq, String name, double x, double y, double z,
        int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        GL11.glPopAttrib();
    }

}
