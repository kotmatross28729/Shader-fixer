package com.kotmatross.shaderfixer.mixins.late.HBM.sedna;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.factory.LegoClient;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = LegoClient.class, priority = 999)
public class MixinLegoClient {

    @Inject(
        method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void renderBulletStandard(Tessellator tess, int dark, int light, double length, double widthF,
        double widthB, boolean fullbright, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "drawLineSegment", at = @At(value = "HEAD"), remap = false)
    private static void drawLineSegment(Tessellator tessellator, double x, double y, double z, double a, double b,
        double c, double iX, double iY, double iZ, double jX, double jZ, CallbackInfo ci) {
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
    }

}
