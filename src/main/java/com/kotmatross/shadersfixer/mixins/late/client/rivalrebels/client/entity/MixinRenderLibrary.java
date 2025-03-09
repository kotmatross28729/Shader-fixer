package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import rivalrebels.client.model.RenderLibrary;

@Mixin(value = RenderLibrary.class, priority = 999)
public class MixinRenderLibrary {

    @Inject(
        method = "renderModel",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderModel(float x1, float y1, float z1, float x, float y, float z, float segDist, float radius,
        int steps, float arcRatio, float rvar, float r, float g, float b, float a, CallbackInfo ci) {
        Utils.Fix();
    }
}
