package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.hbm.render.anim.HbmAnimations;
import com.hbm.util.Clock;

@Mixin(value = HbmAnimations.class, priority = 999)
public class MixinHbmAnimations {

    @Inject(method = "getRelevantTransformation(Ljava/lang/String;I)[D", at = @At(value = "HEAD"), remap = false)
    private static void getRelevantTransformation(String bus, int index, CallbackInfoReturnable<double[]> cir) {
        Clock.update();
    }

}
