package com.kotmatross.shaderfixer.mixins.late.nei;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import codechicken.nei.WorldOverlayRenderer;

@Mixin(value = WorldOverlayRenderer.class, priority = 1005)
public class MixinWorldOverlayRendererLEGACY {

    // FOR ORIGINAL NEI

    @Inject(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", shift = BEFORE),
        remap = false)
    private static void renderMobSpawnOverlay(Entity entity, CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
        ShaderUtils.fix();
    }

    @Inject(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", shift = AFTER),
        remap = false)
    private static void renderMobSpawnOverlay2(Entity entity, CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", ordinal = 0, shift = BEFORE),
        remap = false)
    private static void renderMobSpawnOverlay$programS(Entity entity, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", ordinal = 0, shift = AFTER),
        remap = false)
    private static void renderMobSpawnOverlay$programE(Entity entity, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        ShaderUtils.useProgram(shader_fixer$program.get());
    }

    @Inject(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", shift = BEFORE),
        remap = false)
    private static void renderChunkBounds(Entity entity, CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
        ShaderUtils.fix();
    }

    @Inject(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", shift = AFTER),
        remap = false)
    private static void renderChunkBounds2(Entity entity, CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", ordinal = 0, shift = BEFORE),
        remap = false)
    private static void renderChunkBounds$programS(Entity entity, CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        shader_fixer$program2.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", ordinal = 0, shift = AFTER),
        remap = false)
    private static void renderChunkBounds$programE(Entity entity, CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        ShaderUtils.useProgram(shader_fixer$program2.get());
    }

    @Unique
    private static boolean shader_fixer$lightingM;
    @Unique
    private static boolean shader_fixer$blendingM;

    @Unique
    private static boolean shader_fixer$lightingC;
    @Unique
    private static boolean shader_fixer$blendingC;

    // GETTERS

    @Inject(method = "renderMobSpawnOverlay", at = @At(value = "HEAD"), remap = false)
    private static void shader_fixer$lightingGETM(Entity entity, CallbackInfo ci) {
        shader_fixer$lightingM = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }

    @Inject(method = "renderMobSpawnOverlay", at = @At(value = "HEAD"), remap = false)
    private static void shader_fixer$blendingGETM(Entity entity, CallbackInfo ci) {
        shader_fixer$blendingM = GL11.glGetBoolean(GL11.GL_BLEND);
    }

    @Inject(method = "renderChunkBounds", at = @At(value = "HEAD"), remap = false)
    private static void shader_fixer$lightingGETC(Entity entity, CallbackInfo ci) {
        shader_fixer$lightingC = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }

    @Inject(method = "renderChunkBounds", at = @At(value = "HEAD"), remap = false)
    private static void shader_fixer$blendingGETC(Entity entity, CallbackInfo ci) {
        shader_fixer$blendingC = GL11.glGetBoolean(GL11.GL_BLEND);
    }

    // Only enable/disable if it was on/off in first place

    @WrapWithCondition(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1),
        remap = false)
    private static boolean enableLightingM(int cap) {
        return shader_fixer$lightingM;
    }

    @WrapWithCondition(
        method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2),
        remap = false)
    private static boolean disableBlendingM(int cap) {
        return !shader_fixer$blendingM;
    }

    @WrapWithCondition(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1),
        remap = false)
    private static boolean enableLightingC(int cap) {
        return shader_fixer$lightingC;
    }

    @WrapWithCondition(
        method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2),
        remap = false)
    private static boolean disableBlendingC(int cap) {
        return !shader_fixer$blendingC;
    }
}
