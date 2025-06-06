package com.kotmatross.shadersfixer.mixins.late.client.backhand.sedna;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.shrimp.ShitUtils;

import xonin.backhand.client.hooks.ItemRendererHooks;

@Mixin(value = ItemRendererHooks.class, priority = 999)
public class MixinItemRendererHooks {

    @Inject(method = "renderOffhandReturn", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void renderOffhandReturn(float frame, CallbackInfo ci) {
        if (ShitUtils.shaders_fixer$checkVibe()) {
            ci.cancel();
        }
    }
}
