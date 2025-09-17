package com.kotmatross.shaderfixer.mixins.late.HBM.sedna;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = ItemRenderWeaponBase.class, priority = 808)
public class MixinItemRenderWeaponBase_DEPTH {

    // "Disables disable" (enables) glDepthMask
    // EDIT 17.09.25: Causes explicit z-fighting, disabled by default

    @WrapWithCondition(
        method = "renderMuzzleFlash(JID)V",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glDepthMask (Z)V", ordinal = 0),
        remap = false)
    private static boolean reverseDepth(boolean b) {
        return false;
    }

    @WrapWithCondition(
        method = "renderMuzzleFlash(JID)V",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glDepthMask (Z)V", ordinal = 1),
        remap = false)
    private static boolean reverseDepth2(boolean b) {
        return false;
    }

    @WrapWithCondition(
        method = "renderLaserFlash",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glDepthMask (Z)V", ordinal = 0),
        remap = false)
    private static boolean reverseLasDepth(boolean b) {
        return false;
    }

    @WrapWithCondition(
        method = "renderLaserFlash",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glDepthMask (Z)V", ordinal = 1),
        remap = false)
    private static boolean reverseLasDepth2(boolean b) {
        return false;
    }

}
