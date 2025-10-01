package com.kotmatross.shaderfixer.mixins.late.ZELDASWORDSKILLS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import zeldaswordskills.client.render.entity.RenderEntityHookShot;
import zeldaswordskills.entity.projectile.EntityHookShot;

@Mixin(value = RenderEntityHookShot.class, priority = 999)
public class MixinRenderEntityHookShot {

    @Inject(
        method = "renderLeash",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderLeash(EntityHookShot hookshot, double x, double y, double z, float yaw, float partialTick,
        CallbackInfo ci) {
        Utils.fix();
    }
}
