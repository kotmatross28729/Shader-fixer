package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = WorldOverlayRenderer.class, priority = 1005)
public class MixinWorldOverlayRendererSAFELEGACY {

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
}
