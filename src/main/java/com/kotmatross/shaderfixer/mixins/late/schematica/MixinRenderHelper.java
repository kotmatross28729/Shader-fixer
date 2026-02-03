package com.kotmatross.shaderfixer.mixins.late.schematica;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.schematica.client.renderer.RenderHelper;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface$programS(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(method = "drawCuboidSurface", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidSurface$programE(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        ShaderUtils.useProgram(shader_fixer$program.get());
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline$programS(CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        shader_fixer$program2.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidOutline$programE(CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        ShaderUtils.useProgram(shader_fixer$program2.get());
    }

}
