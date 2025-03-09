package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.core.util.vector.Vector3f;
import com.github.lunatrius.schematica.client.renderer.RenderHelper;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program;

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface$programS(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "drawCuboidSurface", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidSurface$programE(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline(Vector3f zero, Vector3f size, int sides, float red, float green, float blue,
        float alpha, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program2;

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline$programS(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci) {
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "TAIL"), remap = false)
    private static void drawCuboidOutline$programE(Vector3f zero, Vector3f size, int sides, float red, float green,
        float blue, float alpha, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
    }
}
