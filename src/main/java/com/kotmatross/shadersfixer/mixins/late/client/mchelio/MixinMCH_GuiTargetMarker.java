package com.kotmatross.shadersfixer.mixins.late.client.mchelio;

import com.kotmatross.shadersfixer.Utils;
import mcheli.multiplay.MCH_GuiTargetMarker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MCH_GuiTargetMarker.class, priority = 999)
public class MixinMCH_GuiTargetMarker {
    @Inject(method = "drawMark",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void drawMark(CallbackInfo ci) {
        Utils.Fix();
    }
}
