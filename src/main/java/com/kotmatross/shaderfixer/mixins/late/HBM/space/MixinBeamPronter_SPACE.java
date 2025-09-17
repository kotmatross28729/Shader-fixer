package com.kotmatross.shaderfixer.mixins.late.HBM.space;

import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.BeamPronter;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;
import com.kotmatross.shaderfixer.utils.Utils;

@SPEKJORK
@Mixin(value = BeamPronter.class, priority = 999)
public class MixinBeamPronter_SPACE {

    @Inject(
        method = "prontBeam(Lnet/minecraft/util/Vec3;Lcom/hbm/render/util/BeamPronter$EnumWaveType;Lcom/hbm/render/util/BeamPronter$EnumBeamType;IIIIFIFF)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void prontBeam(Vec3 skeleton, BeamPronter.EnumWaveType wave, BeamPronter.EnumBeamType beam,
        int outerColor, int innerColor, int start, int segments, float size, int layers, float thickness, float alpha,
        CallbackInfo ci) {
        Utils.Fix();
    }

    // Fix alpha > 1
    // For some reason, James put 256 as the alpha value (💀™)
    @Redirect(
        method = "setColorWithAlpha",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glColor4f(FFFF)V"))
    private static void transformGLColor(float r, float g, float b, float a) {
        GL11.glColor4f(r, g, b, Math.min(a, 1F));
    }
}
