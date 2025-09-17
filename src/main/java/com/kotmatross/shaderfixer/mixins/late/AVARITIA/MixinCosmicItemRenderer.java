package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import fox.spiteful.avaritia.render.CosmicItemRenderer;

@Mixin(value = CosmicItemRenderer.class, priority = 999)
public class MixinCosmicItemRenderer {
    // !Not working with angelica

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.GLGetCurrentProgram());
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.GLUseProgram(shader_fixer$program.get());
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        shader_fixer$program2.set(Utils.GLGetCurrentProgram());
        shader_fixer$lbx.set(Utils.GetLastBrightnessX());
        shader_fixer$lby.set(Utils.GetLastBrightnessY());
        Utils.EnableFullBrightness();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci,
        @Share("shader_fixer$program2") LocalIntRef shader_fixer$program2,
        @Share("shader_fixer$lbx") LocalFloatRef shader_fixer$lbx,
        @Share("shader_fixer$lby") LocalFloatRef shader_fixer$lby) {
        Utils.GLUseProgram(shader_fixer$program2.get());
        Utils.DisableFullBrightness(shader_fixer$lbx.get(), shader_fixer$lby.get());
    }
}
