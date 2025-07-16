package com.kotmatross.shaderfixer.mixins.late.WEAPONMOD;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import ckathode.weaponmod.entity.projectile.EntityFlail;
import ckathode.weaponmod.render.RenderFlail;

@Mixin(value = RenderFlail.class, priority = 999)
public class MixinRenderFlail {

    @Inject(
        method = "renderFlail",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderFlail(EntityFlail entityflail, double d, double d1, double d2, float f, float f1,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
