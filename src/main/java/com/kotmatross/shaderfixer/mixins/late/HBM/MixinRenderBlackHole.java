package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderBlackHole;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderBlackHole.class, priority = 999)
public class MixinRenderBlackHole {

    @Inject(method = "renderDisc", at = @At(value = "HEAD"), remap = false)
    public void renderDisc(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        shader_fixer$lbx.set(Utils.GetLastBrightnessX());
        shader_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderDisc", at = @At(value = "TAIL"), remap = false)
    public void renderDisc2(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        Utils.DisableFullBrightness(shader_fixer$lbx.get(), shader_fixer$lby.get());
    }

    // Fix for angelica (brightness)
    @Inject(
        method = "renderDisc",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    public void renderDisc3(Entity entity, float interp, CallbackInfo ci, @Local Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
    }

    @Inject(method = "renderSwirl", at = @At(value = "HEAD"), remap = false)
    public void renderSwirl(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$lbx2") LocalFloatRef shader_fixer$lbx2,
        @Share("shader_fixer$lby2") LocalFloatRef shader_fixer$lby2) {
        shader_fixer$lbx2.set(Utils.GetLastBrightnessX());
        shader_fixer$lby2.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderSwirl", at = @At(value = "TAIL"), remap = false)
    public void renderSwirl2(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$lbx2") LocalFloatRef shader_fixer$lbx2,
        @Share("shader_fixer$lby2") LocalFloatRef shader_fixer$lby2) {
        Utils.DisableFullBrightness(shader_fixer$lbx2.get(), shader_fixer$lby2.get());
    }

    // Fix for angelica (brightness)
    @Inject(
        method = "renderSwirl",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.AFTER))
    public void renderSwirl3(Entity entity, float interp, CallbackInfo ci, @Local Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
    }

    @Inject(
        method = "renderJets",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderJetsPR(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderJets",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderJetsPRE(Entity entity, float interp, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(
        method = "renderJets",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderJets(Entity entity, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(
        method = "renderFlare",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderFlare(Entity entity, CallbackInfo ci) {
        Utils.Fix();
    }
}
