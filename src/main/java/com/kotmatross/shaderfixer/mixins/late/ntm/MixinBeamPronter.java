package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.BeamPronter;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

@Mixin(value = BeamPronter.class, priority = 999)
public class MixinBeamPronter {

    @Unique
    private static final String prontBeamDesc = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIF)V";

    @WrapMethod(method = prontBeamDesc, remap = false)
    private static void dontCastShadow(Vec3 skeleton, BeamPronter.EnumWaveType wave, BeamPronter.EnumBeamType beam,
        int outerColor, int innerColor, int start, int segments, float size, int layers, float thickness,
        Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(skeleton, wave, beam, outerColor, innerColor, start, segments, size, layers, thickness);
        }
    }

    @Inject(method = prontBeamDesc, at = @At(value = "HEAD"), remap = false)
    private static void prontBeam(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = prontBeamDesc, at = @At(value = "TAIL"), remap = false)
    private static void prontBeam2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
