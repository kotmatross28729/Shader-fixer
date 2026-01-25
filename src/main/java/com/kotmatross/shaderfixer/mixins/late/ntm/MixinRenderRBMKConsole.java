package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRBMKConsole;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderRBMKConsole.class, priority = 999)
public class MixinRenderRBMKConsole {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void renderTileEntityAt(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderTileEntityAt2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
