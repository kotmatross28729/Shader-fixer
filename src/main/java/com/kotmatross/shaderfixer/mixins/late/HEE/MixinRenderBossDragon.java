package com.kotmatross.shaderfixer.mixins.late.HEE;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.render.entity.RenderBossDragon;

@Mixin(value = RenderBossDragon.class, priority = 999)
public class MixinRenderBossDragon {

    @Inject(
        method = "renderDragonDying",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    protected void renderDragonDying(EntityBossDragon dragon, float partialTickTime, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "renderDragonDying",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    protected void renderDragonDying2(EntityBossDragon dragon, float partialTickTime, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
