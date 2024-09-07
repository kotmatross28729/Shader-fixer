package com.kotmatross.shadersfixer.mixins.late.client.oc.client;

import com.kotmatross.shadersfixer.Utils;
import li.cil.oc.client.renderer.tileentity.HologramRenderer$;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HologramRenderer$.class, priority = 999)
public class MixinHologramRenderer {

    @Inject(method = "draw", at = @At(value = "HEAD"), remap = false)
    private void draw(CallbackInfo ci) {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }
}
