package com.kotmatross.shadersfixer.mixins.late.client.mchelio;

import com.kotmatross.shadersfixer.Utils;
import mcheli.gui.MCH_Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Deprecated
@Mixin(value = MCH_Gui.class, priority = 999)
public class MixinMCH_Gui {
    @Inject(method = "drawTexturedModalRectRotate",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void drawTexturedModalRectRotate(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, float rot, CallbackInfo ci) {
        Utils.Fix();
    }
    @Inject(method = "drawTexturedRect",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void drawTexturedRect(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, double textureWidth, double textureHeight, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "drawLine*",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawLine(double[] line, int color, int mode, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "drawPoints*",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawPoints(double[] points, int color, int pointWidth, CallbackInfo ci) {
        Utils.Fix();
    }
}
