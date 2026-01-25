package com.kotmatross.shaderfixer.mixins.late.opencomputers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import li.cil.oc.client.renderer.tileentity.ScreenRenderer$;

@Mixin(value = ScreenRenderer$.class, priority = 999)
public class MixinScreenRenderer {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private void BeforeDraw(CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/profiler/Profiler;endSection()V",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private void AfterDraw(CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        ShaderUtils.useProgram(shader_fixer$program.get());
    }

}
