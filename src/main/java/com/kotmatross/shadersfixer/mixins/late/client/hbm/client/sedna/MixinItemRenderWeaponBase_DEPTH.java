package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = ItemRenderWeaponBase.class, priority = 808)
public class MixinItemRenderWeaponBase_DEPTH {

    // "Disables disable" (enables) glDepthMask
    // By default, the mixin is not applied, it requires an explicitly enabled config option
    // It should be enabled only when using the resource pack "NTM texture patch for shaders" (removes black background
    // -> doesn't overwrite depth buffer -> doesn't need to be disabled)

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

}
