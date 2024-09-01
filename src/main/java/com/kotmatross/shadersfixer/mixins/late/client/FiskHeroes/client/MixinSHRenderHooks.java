package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client;

import com.fiskmods.heroes.client.SHRenderHooks;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SHRenderHooks.class, priority = 999)
public class MixinSHRenderHooks {

    @Inject(method = "drawLightningLine*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void drawLightningLine(Vec3 start, Vec3 end, float lineWidth, float innerLineWidth, Vec3 color, float scale, float alphaStart, float alphaEnd, boolean ignoreOld, CallbackInfo ci) {
        Utils.Fix();
    }
    @Inject(method = "drawLightningLine*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 1),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 1)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void drawLightningLine2(Vec3 start, Vec3 end, float lineWidth, float innerLineWidth, Vec3 color, float scale, float alphaStart, float alphaEnd, boolean ignoreOld, CallbackInfo ci) {
        Utils.Fix();
    }
    @Inject(method = "drawLightningLine*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 2)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawLightningLine3(Vec3 start, Vec3 end, float lineWidth, float innerLineWidth, Vec3 color, float scale, float alphaStart, float alphaEnd, boolean ignoreOld, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "drawUntexturedRectInternal",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawUntexturedRectInternal(float x, float y, float width, float height, float zLevel, int color, int alpha, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "drawLoadingSquares",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawLoadingSquares(float x, float y, float width, float height, float zLevel, CallbackInfo ci) {
        Utils.Fix();
    }
}
