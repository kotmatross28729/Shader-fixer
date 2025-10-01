package com.kotmatross.shaderfixer.mixins.late.OPENCOMPUTERS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import li.cil.oc.client.renderer.tileentity.HologramRenderer$;

@Mixin(value = HologramRenderer$.class, priority = 999)
public class MixinHologramRenderer {

    @Inject(method = "draw", at = @At(value = "HEAD"), remap = false)
    private void draw(CallbackInfo ci) {
        Utils.fix();
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(method = "draw", at = @At(value = "TAIL"), remap = false)
    private void draw2(CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
