package com.kotmatross.shadersfixer.mixins.late.client.avaritia.client;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import fox.spiteful.avaritia.render.ModelArmorInfinity;

@Mixin(value = ModelArmorInfinity.class, priority = 999)
public class MixinModelArmorInfinity {
    // !Not working with angelica

    @Unique
    public int shaders_fixer$program;
    @Unique
    public float shaders_fixer$lbx;
    @Unique
    public float shaders_fixer$lby;

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
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
        CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(
        method = "func_78088_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V", ordinal = 4, shift = AFTER),
        remap = false)
    private void releaseBrightness(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }

    @Unique
    public int shaders_fixer$program2;

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 1,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
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
        CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
    }

    @Unique
    public float shaders_fixer$lbx2;
    @Unique
    public float shaders_fixer$lby2;

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
        shaders_fixer$lbx2 = Utils.GetLastBrightnessX();
        shaders_fixer$lby2 = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(
        method = "func_78088_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V", ordinal = 6, shift = AFTER),
        remap = false)
    private void releaseBrightness2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
        CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2, shaders_fixer$lby2);
    }
}
