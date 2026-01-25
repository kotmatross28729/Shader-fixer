package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderBlackHole;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = RenderBlackHole.class, priority = 999)
public class MixinRenderBlackHole {

    @Inject(method = "renderDisc", at = @At(value = "HEAD"), remap = false)
    public void renderDisc(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderDisc", at = @At(value = "TAIL"), remap = false)
    public void renderDisc2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    // Fix for angelica (brightness)
    @Inject(
        method = "renderDisc",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    public void renderDisc3(CallbackInfo ci, @Local Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
    }

    @Inject(method = "renderSwirl", at = @At(value = "HEAD"), remap = false)
    public void renderSwirl(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderSwirl", at = @At(value = "TAIL"), remap = false)
    public void renderSwirl2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    // Fix for angelica (brightness)
    @Inject(
        method = "renderSwirl",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    public void renderSwirl3(CallbackInfo ci, @Local Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
    }

    @ModifyArg(
        method = "renderJets",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 2F; // 0.01 SOMEHOW works there, strange
    }

    @Inject(
        method = "renderJets",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    public void renderJets(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderJets",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V",
            ordinal = 1,
            shift = At.Shift.BEFORE),
        remap = false)
    public void renderJets2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
