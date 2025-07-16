package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.particle;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.particle.EntitySHSpellWaveFX;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = EntitySHSpellWaveFX.class, priority = 999)
public class MixinEntitySHSpellWaveFX {

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0))
    public void func_70539_a(Tessellator tessellator, float partialTicks, float f, float f1, float f2, float f3,
        float f4, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    public void func_70539_a_PF(Tessellator tessellator, float partialTicks, float f, float f1, float f2, float f3,
        float f4, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 1,
            shift = At.Shift.AFTER))
    public void func_70539_a_PFE(Tessellator tessellator, float partialTicks, float f, float f1, float f2, float f3,
        float f4, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }

}
