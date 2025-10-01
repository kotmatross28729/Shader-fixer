package com.kotmatross.shaderfixer.mixins.late.MCHELI;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import mcheli.multiplay.MCH_GuiTargetMarker;

@Mixin(value = MCH_GuiTargetMarker.class, priority = 999)
public class MixinMCH_GuiTargetMarker {

    @Inject(
        method = "drawMark",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawMark(CallbackInfo ci) {
        Utils.fix();
    }
}
