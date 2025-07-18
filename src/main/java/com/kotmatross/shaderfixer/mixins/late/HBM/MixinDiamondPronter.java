package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.DiamondPronter;
import com.hbm.render.util.EnumSymbol;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = DiamondPronter.class, priority = 999)
public class MixinDiamondPronter {

    @Inject(
        method = "pront",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 4)),
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    private static void fixLightingWithShaders(int poison, int flammability, int reactivity, EnumSymbol symbol,
        CallbackInfo ci, @Local Tessellator tess) {
        tess.setNormal(1, 0, 0);
    }
}
