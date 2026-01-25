package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(targets = "com/hbm/items/armor/ArmorEnvsuit$1", priority = 999)
public class MixinArmorEnvsuit {

    // Pure fucking bytecode reading simulator : 2
    @Inject(
        method = "renderCommon",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void injectBeforeRenderPart(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderCommon",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    private void injectAfterRenderPart(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
