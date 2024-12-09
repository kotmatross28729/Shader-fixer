package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = WorldOverlayRenderer.class, priority = 1005)
public class MixinWorldOverlayRenderer {

    @Inject(method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderMobSpawnOverlay(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program;

    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 0, shift = BEFORE), remap = false)
    private static void renderMobSpawnOverlay$programS(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = AFTER), remap = false)
    private static void renderMobSpawnOverlay$programE(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "renderChunkBounds",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderChunkBounds(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program2;

    @Inject(method = "renderChunkBounds", at =  @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 0, shift = BEFORE), remap = false)
    private static void renderChunkBounds$programS(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderChunkBounds", at =  @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = AFTER), remap = false)
    private static void renderChunkBounds$programE(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
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

    @Redirect(method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        require = 1, remap = false)
    private static void enableLightingM(int cap) {
        if (cap == GL11.GL_LIGHTING && shaders_fixer$lightingM)
            GL11.glEnable(cap);
    }
    @Redirect(method = "renderMobSpawnOverlay",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        require = 1, remap = false)
    private static void disableBlendingM(int cap) {
        if (cap == GL11.GL_BLEND && !shaders_fixer$blendingM)
            GL11.glDisable(cap);
    }

    @Redirect(method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        require = 1, remap = false)
    private static void enableLightingC(int cap) {
        if (cap == GL11.GL_LIGHTING && shaders_fixer$lightingC)
            GL11.glEnable(cap);
    }
    @Redirect(method = "renderChunkBounds",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        require = 1, remap = false)
    private static void disableBlendingC(int cap) {
        if (cap == GL11.GL_BLEND && !shaders_fixer$blendingC)
            GL11.glDisable(cap);
    }




}
