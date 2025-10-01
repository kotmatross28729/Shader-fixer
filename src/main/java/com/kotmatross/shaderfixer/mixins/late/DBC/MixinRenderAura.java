package com.kotmatross.shaderfixer.mixins.late.DBC;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import JinRyuu.DragonBC.common.Npcs.EntityAura;
import JinRyuu.DragonBC.common.Npcs.RenderAura;

@Mixin(value = RenderAura.class, priority = 999)
public class MixinRenderAura {

    @Inject(
        method = "renderAura",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderAura(EntityAura par1Entity, double parX, double parY, double parZ, float par8, float par9,
        CallbackInfo ci) {
        Utils.fix();
    }
}
