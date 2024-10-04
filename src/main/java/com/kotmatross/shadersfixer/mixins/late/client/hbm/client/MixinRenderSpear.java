package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.entity.effect.RenderSpear;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSpear.class, priority = 999)
public class MixinRenderSpear {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "renderFlash", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 0, shift = At.Shift.BEFORE))
    public void renderFlashPR(double intensity, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderFlash", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = At.Shift.AFTER))
    public void renderFlashPRE(double intensity, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "renderFlash",
        at = @At(value = "HEAD"), remap = false)
    private void renderFlash(double intensity, CallbackInfo ci) {
        Utils.Fix();
    }
}
