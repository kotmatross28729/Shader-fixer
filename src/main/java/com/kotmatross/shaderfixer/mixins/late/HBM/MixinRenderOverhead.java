package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = RenderOverhead.class, priority = 999)
public class MixinRenderOverhead {

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "HEAD"), remap = false)
    private static void drawTag(float offset, double distsq, String name, double x, double y, double z, int dist,
        boolean depthTest, int color, int shadowColor, CallbackInfo ci,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        shader_fixer$lbx.set(Utils.GetLastBrightnessX());
        shader_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "TAIL"), remap = false)
    private static void drawTag2(float offset, double distsq, String name, double x, double y, double z, int dist,
        boolean depthTest, int color, int shadowColor, CallbackInfo ci,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        Utils.DisableFullBrightness(shader_fixer$lbx.get(), shader_fixer$lby.get());
    }

    @Inject(method = "renderThermalSight", at = @At(value = "HEAD"), remap = false)
    private static void renderThermalSight(float partialTicks, CallbackInfo ci,
        @Share("shader_fixer$lbx2") LocalFloatRef shader_fixer$lbx2,
        @Share("shader_fixer$lby2") LocalFloatRef shader_fixer$lby2) {
        shader_fixer$lbx2.set(Utils.GetLastBrightnessX());
        shader_fixer$lby2.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderThermalSight", at = @At(value = "TAIL"), remap = false)
    private static void renderThermalSight2(float partialTicks, CallbackInfo ci,
        @Share("shader_fixer$lbx2") LocalFloatRef shader_fixer$lbx2,
        @Share("shader_fixer$lby2") LocalFloatRef shader_fixer$lby2) {
        Utils.DisableFullBrightness(shader_fixer$lbx2.get(), shader_fixer$lby2.get());
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
    private static void shader_fixer$AttribPush(float offset, double distsq, String name, double x, double y, double z,
        int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
    }

    // Release states
    @Inject(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.BEFORE),
        remap = false)
    private static void shader_fixer$AttribPop(float offset, double distsq, String name, double x, double y, double z,
        int dist, boolean depthTest, int color, int shadowColor, CallbackInfo ci) {
        GL11.glPopAttrib();
    }

}
