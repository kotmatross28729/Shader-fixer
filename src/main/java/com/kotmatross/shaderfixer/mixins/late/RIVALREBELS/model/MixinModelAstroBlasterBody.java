package com.kotmatross.shaderfixer.mixins.late.RIVALREBELS.model;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import rivalrebels.client.model.ModelAstroBlasterBody;

@Mixin(value = ModelAstroBlasterBody.class, priority = 999)
public class MixinModelAstroBlasterBody {

    @Inject(method = "render*", at = @At(value = "HEAD"), remap = false)
    public void render(float size, float red, float green, float blue, float alpha, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
