package com.kotmatross.shaderfixer.mixins.late.DBC;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import JinRyuu.DragonBC.common.Npcs.EntityAura2;
import JinRyuu.DragonBC.common.Npcs.RenderAura2;

@Mixin(value = RenderAura2.class, priority = 999)
public class MixinRenderAura2 {

    @Inject(
        method = "lightning",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    private void lightning(EntityAura2 e, double par2, double par4, double par6, float par9, float var20, float var13,
        boolean rot, CallbackInfo ci) {
        Utils.fix();
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(
        method = "lightning",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    private void lightning2(EntityAura2 e, double par2, double par4, double par6, float par9, float var20, float var13,
        boolean rot, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
