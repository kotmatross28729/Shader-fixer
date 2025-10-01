package com.kotmatross.shaderfixer.mixins.late.ELN;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import mods.eln.transparentnode.NixieTubeDescriptor;

@Mixin(value = NixieTubeDescriptor.class, priority = 999)
public class MixinNixieTubeDescriptor {

    @Inject(method = "draw", at = @At(value = "HEAD"), remap = false)
    private void draw(CallbackInfo ci) {
        Utils.fix();
    }
}
