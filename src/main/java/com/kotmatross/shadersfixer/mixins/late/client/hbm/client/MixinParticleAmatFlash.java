package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.particle.ParticleAmatFlash;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = ParticleAmatFlash.class, priority = 999)
public class MixinParticleAmatFlash {

    @Inject(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", shift = At.Shift.AFTER),
        remap = false)
    private void func_70539_a(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void func_70539_aPR(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void func_70539_aPRE(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }

    @Inject(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", shift = At.Shift.BEFORE),
        remap = false)
    public void func_70539_a1(Tessellator tess, float interp, float x, float y, float z, float tx, float tz,
        CallbackInfo ci) {
        Utils.EnableFullBrightness();
    }
}
