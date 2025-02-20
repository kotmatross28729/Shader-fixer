package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = WorldOverlayRenderer.class, priority = 1005)
public class MixinWorldOverlayRendererLEGACY {

    //FOR ORIGINAL NEI
    
    @Inject(method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V"), remap = false)
    private static void renderMobSpawnOverlay(Entity entity, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    @Unique
    private static int shaders_fixer$program;

    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", ordinal = 0, shift = BEFORE), remap = false)
    private static void renderMobSpawnOverlay$programS(Entity entity, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", ordinal = 0, shift = AFTER), remap = false)
    private static void renderMobSpawnOverlay$programE(Entity entity, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "renderChunkBounds",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V"), remap = false)
    private static void renderChunkBounds(Entity entity, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    @Unique
    private static int shaders_fixer$program2;

    @Inject(method = "renderChunkBounds", at =  @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V", ordinal = 0, shift = BEFORE), remap = false)
    private static void renderChunkBounds$programS(Entity entity, CallbackInfo ci) {
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderChunkBounds", at =  @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnd()V", ordinal = 0, shift = AFTER), remap = false)
    private static void renderChunkBounds$programE(Entity entity, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
    }
    @Unique
    private static boolean shaders_fixer$lightingM;
    @Unique
    private static boolean shaders_fixer$blendingM;
    
    @Unique
    private static boolean shaders_fixer$lightingC;
    @Unique
    private static boolean shaders_fixer$blendingC;
    
    //GETTERS
    
    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$lightingGETM(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$lightingM = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }
    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$blendingGETM(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$blendingM = GL11.glGetBoolean(GL11.GL_BLEND);
    }
    
    @Inject(method = "renderChunkBounds", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$lightingGETC(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$lightingC = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }
    @Inject(method = "renderChunkBounds", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$blendingGETC(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$blendingC = GL11.glGetBoolean(GL11.GL_BLEND);
    }
    
    //Only enable/disable if it was on/off in first place
    
    @WrapWithCondition(
            method = "renderMobSpawnOverlay",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1), remap = false
    )
    private static boolean enableLightingM(int cap) {
        return shaders_fixer$lightingM;
    }
    
    @WrapWithCondition(
            method = "renderMobSpawnOverlay",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2), remap = false
    )
    private static boolean disableBlendingM(int cap) {
        return !shaders_fixer$blendingM;
    }
    
    
    @WrapWithCondition(
            method = "renderChunkBounds",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1), remap = false
    )
    private static boolean enableLightingC(int cap) {
        return shaders_fixer$lightingC;
    }
    
    @WrapWithCondition(
            method = "renderChunkBounds",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2), remap = false
    )
    private static boolean disableBlendingC(int cap) {
        return !shaders_fixer$blendingC;
    }
}
