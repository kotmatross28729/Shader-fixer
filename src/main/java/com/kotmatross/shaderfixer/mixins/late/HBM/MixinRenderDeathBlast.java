package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderDeathBlast;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderDeathBlast.class, priority = 999)
public class MixinRenderDeathBlast {

    @Inject(
        method = "func_76986_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_76986_aPR(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(
        method = "func_76986_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V",
            ordinal = 0,
            shift = At.Shift.AFTER),
        remap = false)
    private void func_76986_aPRE(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "func_76986_a", at = @At(value = "TAIL"), remap = false)
    public void func_76986_a2(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.fix();
    }
}
