package com.kotmatross.shaderfixer.mixins.late.ntm.space;

import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.BeamPronter;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

@SPEKJORK
@Mixin(value = BeamPronter.class, priority = 999)
public class MixinBeamPronter_SPACE {

    @Unique
    private static final String prontBeamDescSpace = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIFF)V";

    @WrapMethod(method = prontBeamDescSpace, remap = false)
    private static void dontCastShadow(Vec3 skeleton, BeamPronter.EnumWaveType wave, BeamPronter.EnumBeamType beam,
        int outerColor, int innerColor, int start, int segments, float size, int layers, float thickness, float alpha,
        Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original
                .call(skeleton, wave, beam, outerColor, innerColor, start, segments, size, layers, thickness, alpha);
        }
    }

    @Inject(method = prontBeamDescSpace, at = @At(value = "HEAD"), remap = false)
    private static void prontBeam(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = prontBeamDescSpace, at = @At(value = "TAIL"), remap = false)
    private static void prontBeam2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    // Fix alpha != 1
    // For some reason, James put 256 (and 0.5) as the alpha value
    @Redirect(
        method = "setColorWithAlpha",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glColor4f(FFFF)V"),
        remap = false)
    private static void transformGLColor(float r, float g, float b, float a) {
        GL11.glColor4f(r, g, b, 1F);
    }

}
