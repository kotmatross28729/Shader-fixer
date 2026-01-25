package com.kotmatross.shaderfixer.mixins.late.opencomputers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import li.cil.oc.util.RenderState$;

@Mixin(value = RenderState$.class, priority = 999)
public class MixinRenderState_disableDisplayList {

    /**
     * Forces OC to not use display lists
     * <p>
     * Display lists appear to be broken in Angelica (with shaders?)
     * <p>
     * Temporary workaround(!!!); proper fix should be investigated on Angelica's side
     */
    @Inject(method = "compilingDisplayList", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private void compilingDisplayList(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
