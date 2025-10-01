package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import fox.spiteful.avaritia.render.ModelArmorInfinity;

@Mixin(value = ModelArmorInfinity.class, priority = 999)
public class MixinModelArmorInfinity {
    // !Not working with angelica

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
    }

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(
        method = "func_78088_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V", ordinal = 4, shift = AFTER),
        remap = false)
    private void releaseBrightness(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 1,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci, @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        shader_fixer$program2.set(Utils.ProgramUtils.GLGetCurrentProgram());
    }

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V",
            ordinal = 1,
            shift = AFTER),
        remap = false)
    private void afterUseShader2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci, @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program2.get());
    }

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/ModelArmorInfinity;setWings()V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void WINGSBrightness(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(
        method = "func_78088_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V", ordinal = 6, shift = AFTER),
        remap = false)
    private void releaseBrightness2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
