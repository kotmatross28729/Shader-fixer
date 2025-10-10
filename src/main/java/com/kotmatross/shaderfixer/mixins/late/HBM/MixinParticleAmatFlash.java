package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.particle.ParticleAmatFlash;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;

@Mixin(value = ParticleAmatFlash.class, priority = 999)
public class MixinParticleAmatFlash {

    @Inject(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", shift = At.Shift.AFTER),
        remap = false)
    private void func_70539_a(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci) {
        Utils.fix();
    }

    @ModifyArg(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha, @Local(ordinal = 4) LocalDoubleRef inverse) {
        return alpha == 0 ? (float) inverse.get() / 2F : alpha * 10F;
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_70539_a1(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    public void func_70539_a2(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

}
