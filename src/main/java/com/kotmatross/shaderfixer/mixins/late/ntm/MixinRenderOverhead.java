package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = RenderOverhead.class, priority = 999)
public class MixinRenderOverhead {

    @WrapWithCondition(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glNormal3f(FFF)V"),
        remap = false)
    private static boolean disableNormalsSetup(float nx, float ny, float nz) {
        return false; // Peak mjoang coding v2
    }

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "HEAD"), remap = false)
    private static void drawTag(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "drawTag(FDLjava/lang/String;DDDIZII)V", at = @At(value = "TAIL"), remap = false)
    private static void drawTag2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(method = "renderThermalSight", at = @At(value = "HEAD"), remap = false)
    private static void renderThermalSight(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderThermalSight", at = @At(value = "TAIL"), remap = false)
    private static void renderThermalSight2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    /**
     * Fixes GL state leak with angelica
     */
    @Inject(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", shift = At.Shift.AFTER),
        remap = false)
    private static void shader_fixer$AttribPush(CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
    }

    @Inject(
        method = "drawTag(FDLjava/lang/String;DDDIZII)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.BEFORE),
        remap = false)
    private static void shader_fixer$AttribPop(CallbackInfo ci) {
        GL11.glPopAttrib();
    }

}
