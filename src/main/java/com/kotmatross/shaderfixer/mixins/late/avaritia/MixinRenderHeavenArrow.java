package com.kotmatross.shaderfixer.mixins.late.avaritia;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;

import fox.spiteful.avaritia.render.RenderHeavenArrow;

@Mixin(value = RenderHeavenArrow.class, priority = 999)
public class MixinRenderHeavenArrow {

    @Inject(method = "doRender", at = @At(value = "HEAD"))
    private void doRender(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "doRender", at = @At(value = "TAIL"))
    private void doRender_E(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
