package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.factory.LegoClient;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = LegoClient.class, priority = 999)
public class MixinLegoClient {

    @Inject(
        method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void renderBulletStandard(CallbackInfo ci, @Local(argsOnly = true) boolean fullbright) {
        if (fullbright) ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(value = "TAIL"),
        remap = false)
    private static void renderBulletStandard2(CallbackInfo ci, @Local(argsOnly = true) boolean fullbright) {
        if (fullbright) ShaderUtils.disableFullBrightness();
    }

    @Inject(
        method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    private static void renderBulletStandard3(CallbackInfo ci, @Local(argsOnly = true) Tessellator tess) {
        if (AngelicaUtils.isShaderEnabled()) tess.setBrightness(240);
    }

    @Inject(method = "drawLineSegment", at = @At(value = "HEAD"), remap = false)
    private static void drawLineSegment(CallbackInfo ci, @Local(argsOnly = true) Tessellator tessellator) {
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
    }

}
