package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.particle.ParticleAmatFlash;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = ParticleAmatFlash.class, priority = 999)
public class MixinParticleAmatFlash {

    @ModifyArg(
        method = "renderParticle",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha, @Local(ordinal = 4) double inverse) {
        return alpha == 0 ? (float) inverse / 2F : alpha * 10F;
    }

    @Inject(
        method = "renderParticle",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    public void renderParticle1(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderParticle",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    public void renderParticle2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
