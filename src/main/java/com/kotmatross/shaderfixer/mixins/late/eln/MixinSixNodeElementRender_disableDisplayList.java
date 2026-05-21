package com.kotmatross.shaderfixer.mixins.late.eln;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import mods.eln.node.six.SixNodeElementRender;

@Mixin(value = SixNodeElementRender.class, priority = 999, remap = false)
public class MixinSixNodeElementRender_disableDisplayList {

    @Shadow
    public void glListDraw() {}

    /**
     * Forces ELN to not use display lists
     */
    @WrapMethod(method = "glListCall")
    private void glListCall(Operation<Void> original) {
        this.glListDraw();
    }

}
