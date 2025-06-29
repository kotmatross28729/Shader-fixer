package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.space;

import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.BeamPronter;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.shrimp.SPEKJORK;

@SPEKJORK
@Mixin(value = BeamPronter.class, priority = 999)
public class MixinBeamPronter {

    @Inject(
        method = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIFF)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void prontBeam(Vec3 skeleton, BeamPronter.EnumWaveType wave, BeamPronter.EnumBeamType beam,
        int outerColor, int innerColor, int start, int segments, float size, int layers, float thickness, float alpha,
        CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
