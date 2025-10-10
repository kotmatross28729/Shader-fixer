package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderSpear;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderSpear.class, priority = 999)
public class MixinRenderSpear {

    // TODO: alpha fix

    @Inject(
        method = "renderFlash",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    public void renderFlashPR(double intensity, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderFlash",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    public void renderFlashPRE(double intensity, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(method = "renderFlash", at = @At(value = "HEAD"), remap = false)
    private void renderFlash(double intensity, CallbackInfo ci) {
        Utils.fix();
    }
}
