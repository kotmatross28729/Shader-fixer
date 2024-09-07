package com.kotmatross.shadersfixer.mixins.late.client.eln.client;

import com.kotmatross.shadersfixer.Utils;
import mods.eln.transparentnode.NixieTubeDescriptor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NixieTubeDescriptor.class, priority = 999)
public class MixinNixieTubeDescriptor {
    @Inject(method = "draw", at = @At(value = "HEAD"), remap = false)
    private void draw(CallbackInfo ci) {
        Utils.Fix();
    }
}
