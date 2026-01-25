package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRBMKLid;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderRBMKLid.class, priority = 999)
public class MixinRenderRBMKLid {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void func_147500_a(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void func_147500_a2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @ModifyConstant(method = "renderTileEntityAt", constant = @Constant(floatValue = 0.1F), remap = false)
    public float alphaFix(float brightness) {
        return 0.4F;
    }

}
