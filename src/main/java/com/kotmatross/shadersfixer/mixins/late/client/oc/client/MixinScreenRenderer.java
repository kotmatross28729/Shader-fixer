package com.kotmatross.shadersfixer.mixins.late.client.oc.client;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import li.cil.oc.client.renderer.tileentity.ScreenRenderer$;

@Mixin(value = ScreenRenderer$.class, priority = 999)
public class MixinScreenRenderer {
    // !Not working with angelica

    // THE FUCK SCALA ADDS THIS FUCKING "$" - JUST TO BREAK ALL THE METHODS ACCESS???
    @Inject(
        method = "draw",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V", shift = At.Shift.AFTER),
        remap = false)
    private void draw(CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private void BeforeDraw(TileEntity t, double x, double y, double z, float f, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram(); // This: bring back normal colors (no yellow-ish), fixes brightness, real guy
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/profiler/Profiler;endSection()V",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private void AfterDraw(TileEntity t, double x, double y, double z, float f, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }
}
