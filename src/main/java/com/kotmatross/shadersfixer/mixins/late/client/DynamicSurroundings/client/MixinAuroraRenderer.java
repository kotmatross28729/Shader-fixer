package com.kotmatross.shadersfixer.mixins.late.client.DynamicSurroundings.client;

import org.blockartistry.mod.DynSurround.client.aurora.Aurora;
import org.blockartistry.mod.DynSurround.client.aurora.AuroraRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

@Mixin(value = AuroraRenderer.class, priority = 999)
public class MixinAuroraRenderer {

    @Unique
    private static int shaders_fixer$program;

    @Inject(
        method = "renderAurora",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private static void renderAuroraPR(final float partialTick, final Aurora aurora, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderAurora",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private static void renderAuroraPRE(final float partialTick, final Aurora aurora, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "renderAurora", at = @At(value = "HEAD"), remap = false)
    private static void renderAurora(final float partialTick, final Aurora aurora, CallbackInfo ci) {
        // Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
