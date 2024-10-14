package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.shrimp.nonsense.Fucked;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingShit;
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

@Fucked @FuckingCursed @FuckingShit
@Mixin(value = WorldOverlayRenderer.class, priority = 999)
public class MixinWorldOverlayRendererLEGACY {

    @Inject(method = "renderMobSpawnOverlay",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnd()V",
                ordinal = 0)),
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
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnd()V",
                ordinal = 0)),
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
    private static boolean shaders_fixer$lighting;
    @Unique
    private static boolean shaders_fixer$blending;


    @Inject(method = "renderMobSpawnOverlay", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$lightingGET(Entity entity, CallbackInfo ci) {
        shaders_fixer$lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }

    @Inject(method = "renderChunkBounds", at =  @At(value = "HEAD"), remap = false)
    private static void shaders_fixer$blendingGET(Entity entity, CallbackInfo ci) {
        shaders_fixer$blending = GL11.glGetBoolean(GL11.GL_BLEND);
    }

    @Redirect(method = {"renderMobSpawnOverlay", "renderChunkBounds"},
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        require = 1, remap = false)
    private static void enableLighting(int cap) {
        if (cap == GL11.GL_LIGHTING && shaders_fixer$lighting)
            GL11.glEnable(cap);
    }
    @Redirect(method = {"renderMobSpawnOverlay", "renderChunkBounds"},
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        require = 1, remap = false)
    private static void disableBlending(int cap) {
        if (cap == GL11.GL_BLEND && !shaders_fixer$blending)
            GL11.glDisable(cap);
    }
}
