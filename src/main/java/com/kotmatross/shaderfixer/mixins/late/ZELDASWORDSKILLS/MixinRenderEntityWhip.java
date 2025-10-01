package com.kotmatross.shaderfixer.mixins.late.ZELDASWORDSKILLS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import zeldaswordskills.client.render.entity.RenderEntityWhip;
import zeldaswordskills.entity.projectile.EntityWhip;

@Mixin(value = RenderEntityWhip.class, priority = 999)
public class MixinRenderEntityWhip {

    @Inject(
        method = "renderLeash",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderLeash(EntityWhip whip, double x, double y, double z, float yaw, float partialTick,
        CallbackInfo ci) {
        Utils.fix();
    }
}
