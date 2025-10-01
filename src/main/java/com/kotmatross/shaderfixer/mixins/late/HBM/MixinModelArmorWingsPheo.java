package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.model.ModelArmorWingsPheo;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ModelArmorWingsPheo.class, priority = 999)
public class MixinModelArmorWingsPheo {

    @Inject(method = "renderFlame", at = @At(value = "HEAD"), remap = false)
    private static void renderFlame(CallbackInfo ci) {
        Utils.fix();
    }

}
