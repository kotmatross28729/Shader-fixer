package com.kotmatross.shaderfixer.mixins.late.OPENCOMPUTERS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import li.cil.oc.util.RenderState$;

@Mixin(value = RenderState$.class, priority = 999)
public class MixinRenderState {

    @Inject(method = "disableLighting", at = @At(value = "HEAD"), remap = false)
    private void disableLight(CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightnessSafe();
    }

}
