package com.kotmatross.shaderfixer.mixins.late.ELN;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import mods.eln.node.six.SixNodeElementRender;

@Mixin(value = SixNodeElementRender.class, priority = 999)
public class MixinSixNodeElementRender_disableDisplayList {

    @Shadow
    public void glListDraw() {}

    /**
     * Forces ELN to not use display lists
     * <p>
     * Display lists appear to be broken in Angelica (with shaders?)
     * <p>
     * Temporary workaround(!!!); proper fix should be investigated on Angelica's side
     */
    @WrapMethod(method = "glListCall", remap = false)
    private void glListCall(Operation<Void> original) {
        this.glListDraw();
    }

}
