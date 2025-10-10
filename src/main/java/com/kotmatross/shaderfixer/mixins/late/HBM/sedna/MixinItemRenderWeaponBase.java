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
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@FuckingCursed
@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe, NTMRenderGetters {

    @Inject(method = "renderSmokeNodes", at = @At(value = "HEAD"), remap = false)
    private static void renderSmokeNodes(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        Utils.fix();
    }

    // TODO: alpha fix

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private static void renderSmokeNodesPR(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderSmokeNodes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private static void renderSmokeNodesPRE(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(method = "renderGapFlash", at = @At(value = "HEAD"), remap = false)
    private static void renderGapFlash(long lastShot, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(method = "renderGapFlash", at = @At(value = "TAIL"), remap = false)
    private static void renderGapFlash2(long lastShot, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "HEAD"), remap = false)
    private static void renderMuzzleFlash(long lastShot, int duration, double l, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(method = "renderMuzzleFlash(JID)V", at = @At(value = "TAIL"), remap = false)
    private static void renderMuzzleFlash2(long lastShot, int duration, double l, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
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
    public float shader_fixer$getGunsBaseFOV(ItemStack stack) {
        return getBaseFOV(stack);
    }

    @Override
    public float shader_fixer$getGunsSwayMagnitude(ItemStack stack) {
        return getSwayMagnitude(stack);
    }

    @Override
    public float shader_fixer$getGunsSwayPeriod(ItemStack stack) {
        return getSwayPeriod(stack);
    }

    @Override
    public float shader_fixer$getGunsTurnMagnitude(ItemStack stack) {
        return getTurnMagnitude(stack);
    }

}
