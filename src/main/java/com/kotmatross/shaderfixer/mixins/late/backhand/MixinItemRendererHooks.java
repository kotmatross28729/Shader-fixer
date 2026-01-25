package com.kotmatross.shaderfixer.mixins.late.backhand;

import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;

import xonin.backhand.client.hooks.ItemRendererHooks;

@Mixin(value = ItemRendererHooks.class, priority = 999)
public class MixinItemRendererHooks {

    @Inject(method = "renderOffhandReturn", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void renderOffhandReturn(CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            ci.cancel();
        }
    }

}
