package com.kotmatross.shaderfixer.mixins.late.ELN;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import mods.eln.misc.UtilsClient;

@Mixin(value = UtilsClient.class, priority = 999)
public class MixinUtilsClient {

    @Inject(method = "disableLight", at = @At(value = "HEAD"), remap = false)
    private static void disableLight(CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(method = "enableLight", at = @At(value = "HEAD"), remap = false)
    private static void enableLight(CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
