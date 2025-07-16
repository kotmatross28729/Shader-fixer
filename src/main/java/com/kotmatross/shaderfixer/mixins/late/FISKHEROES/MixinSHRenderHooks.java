package com.kotmatross.shaderfixer.mixins.late.FISKHEROES;

import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.SHRenderHooks;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = SHRenderHooks.class, priority = 999)
public class MixinSHRenderHooks {

    @Inject(method = "drawLightningLine*", at = @At(value = "HEAD"), remap = false)
    private static void drawLightningLine(Vec3 start, Vec3 end, float lineWidth, float innerLineWidth, Vec3 color,
        float scale, float alphaStart, float alphaEnd, boolean ignoreOld, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "drawUntexturedRectInternal",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawUntexturedRectInternal(float x, float y, float width, float height, float zLevel, int color,
        int alpha, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "drawLoadingSquares",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawLoadingSquares(float x, float y, float width, float height, float zLevel, CallbackInfo ci) {
        Utils.Fix();
    }
}
