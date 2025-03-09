package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.factory.LegoClient;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = LegoClient.class, priority = 999)
public class MixinLegoClient {

    @Inject(
        method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void renderBulletStandard(Tessellator tess, int dark, int light, double length, double widthF,
        double widthB, boolean fullbright, CallbackInfo ci) {
        if (fullbright) {
            Utils.EnableFullBrightness();
        }
        Utils.Fix();
    }

    @Unique
    private static float shaders_fixer$lbx;
    @Unique
    private static float shaders_fixer$lby;

    @Inject(method = "renderFlareSprite", at = @At(value = "HEAD"), remap = false)
    private static void renderFlareSprite(Entity bullet, float interp, float r, float g, float b, double scale,
        float outerAlpha, float innerAlpha, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderFlareSprite", at = @At(value = "TAIL"), remap = false)
    private static void renderFlareSprite2(Entity bullet, float interp, float r, float g, float b, double scale,
        float outerAlpha, float innerAlpha, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }

}
