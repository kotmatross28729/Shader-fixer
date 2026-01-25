package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.particle.ParticleDebugLine;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = ParticleDebugLine.class, priority = 999)
public class MixinParticleDebugLine {

    @Inject(
        method = "renderParticle",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void renderParticle(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderParticle",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderParticle2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
