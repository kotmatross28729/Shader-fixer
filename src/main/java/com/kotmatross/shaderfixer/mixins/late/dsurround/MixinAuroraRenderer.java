package com.kotmatross.shaderfixer.mixins.late.dsurround;

import org.blockartistry.mod.DynSurround.client.aurora.AuroraRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = AuroraRenderer.class, priority = 999)
public class MixinAuroraRenderer {

    @Inject(
        method = "renderAurora",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private static void renderAuroraPR(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(
        method = "renderAurora",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private static void renderAuroraPRE(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        ShaderUtils.useProgram(shader_fixer$program.get());
    }

}
