package com.kotmatross.shaderfixer.mixins.late.HBM.sedna;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.NTMRenderGetters;
import com.kotmatross.shaderfixer.shrimp.Vibe;
import com.kotmatross.shaderfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@FuckingCursed
@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe, NTMRenderGetters {

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

    @Shadow
    protected float getBaseFOV(ItemStack stack) {
        return 70.0F;
    }

    @Shadow
    protected float getSwayMagnitude(ItemStack stack) {
        return ItemGunBaseNT.getIsAiming(stack) ? 0.1F : 0.5F;
    }

    @Shadow
    protected float getSwayPeriod(ItemStack stack) {
        return 0.75F;
    }

    @Shadow
    protected float getTurnMagnitude(ItemStack stack) {
        return 2.75F;
    }

    @Override
    public float shaders_fixer$getGunsBaseFOV(ItemStack stack) {
        return getBaseFOV(stack);
    }

    @Override
    public float shaders_fixer$getGunsSwayMagnitude(ItemStack stack) {
        return getSwayMagnitude(stack);
    }

    @Override
    public float shaders_fixer$getGunsSwayPeriod(ItemStack stack) {
        return getSwayPeriod(stack);
    }

    @Override
    public float shaders_fixer$getGunsTurnMagnitude(ItemStack stack) {
        return getTurnMagnitude(stack);
    }

}
