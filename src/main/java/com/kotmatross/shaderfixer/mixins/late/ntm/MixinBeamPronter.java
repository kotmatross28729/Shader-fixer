package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.BeamPronter;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = BeamPronter.class, priority = 999)
public class MixinBeamPronter {

    @Inject(
        method = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIF)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void prontBeam(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIF)V",
        at = @At(value = "TAIL"),
        remap = false)
    private static void prontBeam2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
