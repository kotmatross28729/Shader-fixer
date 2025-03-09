package com.kotmatross.shadersfixer.mixins.late.client.HEE;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.render.entity.RenderBossDragon;

@Mixin(value = RenderBossDragon.class, priority = 999)
public class MixinRenderBossDragon {

    @Inject(
        method = "renderDragonDying",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderDragonDying(EntityBossDragon dragon, float partialTickTime, CallbackInfo ci) {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }
}
