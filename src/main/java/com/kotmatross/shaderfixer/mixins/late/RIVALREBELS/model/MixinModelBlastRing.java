package com.kotmatross.shaderfixer.mixins.late.RIVALREBELS.model;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import rivalrebels.client.model.ModelBlastRing;

@Mixin(value = ModelBlastRing.class, priority = 999)
public class MixinModelBlastRing {

    @Inject(method = "renderModel*", at = @At(value = "HEAD"), remap = false)
    public void renderModel(float size, int segments, float thickness, float height, float pitch, float yaw, float roll,
        float x, float y, float z, CallbackInfo ci) {
        Utils.Fix();
    }
}
