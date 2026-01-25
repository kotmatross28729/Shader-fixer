package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderMachineForceField;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = RenderMachineForceField.class, priority = 999)
public class MixinRenderMachineForceField {

    @WrapWithCondition(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lcom/hbm/render/tileentity/RenderMachineForceField;generateSphere(IIFI)V"),
        remap = false)
    private boolean dontCastShadow(RenderMachineForceField instance, int l, int s, float rad, int hex) {
        return !AngelicaUtils.isShadowPass();
    }

    @Inject(
        method = "generateSphere",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void generateSphere(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "generateSphere",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void generateSphere22(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(
        method = "generateSphere2",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void generateSphere2(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "generateSphere2",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void generateSphere222(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
