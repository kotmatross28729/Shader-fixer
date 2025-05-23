package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import java.lang.annotation.Annotation;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.shrimp.Vibe;

@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe {

    @Override
    public Class<? extends Annotation> annotationType() {
        return Vibe.class;
    }

    @Inject(method = "renderSmokeNodes", at = @At(value = "HEAD"), remap = false)
    private static void renderSmokeNodes(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program;

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private static void renderSmokeNodesPR(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private static void renderSmokeNodesPRE(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Unique
    private static float shaders_fixer$lbx;
    @Unique
    private static float shaders_fixer$lby;

    @Inject(method = "renderGapFlash", at = @At(value = "HEAD"), remap = false)
    private static void renderGapFlash(long lastShot, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderGapFlash", at = @At(value = "TAIL"), remap = false)
    private static void renderGapFlash2(long lastShot, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }

    @Unique
    private static float shaders_fixer$lbx2;
    @Unique
    private static float shaders_fixer$lby2;

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "HEAD"), remap = false)
    private static void renderMuzzleFlash(long lastShot, int duration, double l, CallbackInfo ci) {
        shaders_fixer$lbx2 = Utils.GetLastBrightnessX();
        shaders_fixer$lby2 = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "TAIL"), remap = false)
    private static void renderMuzzleFlash2(long lastShot, int duration, double l, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2, shaders_fixer$lby2);
    }

}
