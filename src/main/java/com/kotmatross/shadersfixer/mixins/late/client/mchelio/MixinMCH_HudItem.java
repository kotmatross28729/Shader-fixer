package com.kotmatross.shadersfixer.mixins.late.client.mchelio;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import mcheli.hud.MCH_HudItem;

@Mixin(value = MCH_HudItem.class, priority = 999)
public class MixinMCH_HudItem {

    @Inject(
        method = "drawRect",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private static void drawRect(double par0, double par1, double par2, double par3, int par4, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "drawLine*",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawLine(double[] line, int color, int mode, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "drawPoints",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawPoints(ArrayList points, int color, int pointWidth, CallbackInfo ci) {
        Utils.Fix();
    }
}
