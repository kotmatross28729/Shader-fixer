package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@FuckingCursed
@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public abstract class MixinItemRenderWeaponBase implements Vibe {
    // "implements annotation interface" - shut the fuck up

    @Inject(method = "renderSmokeNodes", at = @At(value = "HEAD"), remap = false)
    private static void renderSmokeNodes(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private static void renderSmokeNodesPR(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private static void renderSmokeNodesPRE(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }

    @Inject(method = "renderGapFlash", at = @At(value = "HEAD"), remap = false)
    private static void renderGapFlash(long lastShot, CallbackInfo ci,
        @Share("shaders_fixer$lbx") LocalFloatRef shaders_fixer$lbx,
        @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
        shaders_fixer$lbx.set(Utils.GetLastBrightnessX());
        shaders_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderGapFlash", at = @At(value = "TAIL"), remap = false)
    private static void renderGapFlash2(long lastShot, CallbackInfo ci,
        @Share("shaders_fixer$lbx") LocalFloatRef shaders_fixer$lbx,
        @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
        Utils.DisableFullBrightness(shaders_fixer$lbx.get(), shaders_fixer$lby.get());
    }

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "HEAD"), remap = false)
    private static void renderMuzzleFlash(long lastShot, int duration, double l, CallbackInfo ci,
        @Share("shaders_fixer$lbx2") LocalFloatRef shaders_fixer$lbx2,
        @Share("shaders_fixer$lby2") LocalFloatRef shaders_fixer$lby2) {
        shaders_fixer$lbx2.set(Utils.GetLastBrightnessX());
        shaders_fixer$lby2.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
    }

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "TAIL"), remap = false)
    private static void renderMuzzleFlash2(long lastShot, int duration, double l, CallbackInfo ci,
        @Share("shaders_fixer$lbx2") LocalFloatRef shaders_fixer$lbx2,
        @Share("shaders_fixer$lby2") LocalFloatRef shaders_fixer$lby2) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2.get(), shaders_fixer$lby2.get());
    }

}
