package com.kotmatross.shaderfixer.mixins.late.backhand;

import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;

import xonin.backhand.client.utils.BackhandRenderHelper;

@Mixin(value = BackhandRenderHelper.class, priority = 999)
public class MixinBackhandRenderHelper {

    @Inject(method = "renderOffhandItemIn3rdPerson", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void renderOffhandItemIn3rdPerson(CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED)) {
            ci.cancel();
        }
    }

}
