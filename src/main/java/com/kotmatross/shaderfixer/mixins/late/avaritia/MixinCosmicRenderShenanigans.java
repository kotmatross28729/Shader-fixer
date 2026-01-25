package com.kotmatross.shaderfixer.mixins.late.avaritia;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;

import fox.spiteful.avaritia.render.CosmicRenderShenanigans;

@Mixin(value = CosmicRenderShenanigans.class, priority = 999)
public class MixinCosmicRenderShenanigans {

    @Unique
    private static int shader_fixer$previousProgram;

    @Inject(method = "useShader", at = @At(value = "HEAD"), remap = false)
    private static void useShader(CallbackInfo ci) {
        shader_fixer$previousProgram = ShaderUtils.getCurrentProgram();
    }

    @Inject(method = "releaseShader", at = @At(value = "TAIL"), remap = false)
    private static void releaseShader(CallbackInfo ci) {
        ShaderUtils.useProgram(shader_fixer$previousProgram);
    }

}
