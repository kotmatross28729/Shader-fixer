package com.kotmatross.shaderfixer.mixins.late.SCHEMATICA;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.core.util.vector.Vector3f;
import com.github.lunatrius.schematica.client.renderer.RenderHelper;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "drawCuboidSurface", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidSurface2(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface$programS(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(method = "drawCuboidSurface", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidSurface$programE(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidOutline2(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline$programS(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci, @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        shader_fixer$program2.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidOutline$programE(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci, @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program2.get());
    }
}
