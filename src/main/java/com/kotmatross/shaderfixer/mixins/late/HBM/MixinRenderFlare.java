package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderFlare;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderFlare.class, priority = 999)
public class MixinRenderFlare {

    @Inject(
        method = "func_76986_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    public void func_76986_aPR(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "func_76986_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    public void func_76986_aPRE(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }

    @Inject(
        method = "func_76986_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
