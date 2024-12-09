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
public class MixinWorldOverlayRendererSAFE {

    @Inject(method = "renderMobSpawnOverlay",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
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
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
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
}
