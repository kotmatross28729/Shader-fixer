package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.sedna;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;
import com.kotmatross.shadersfixer.shrimp.ShitUtils;
import com.llamalad7.mixinextras.sugar.Local;

/**
 * Side NTM gun things (interpolation / fov / misc GL stuff)
 *
 * @author kotmatross
 */
@Mixin(value = EntityRenderer.class, priority = 1003)
public class MixinEntityRenderer {
    // FOR VANILLA / PARTIALLY (ANGEL)ICA (NOT FOR SHITFINE)

    // Vanilla only
    @Inject(method = "renderHand", at = @At(value = "HEAD"))
    public void HandleInterp(float interp, int p_78476_2_, CallbackInfo ci) {
        if (ShitUtils.shaders_fixer$checkVibe()) {
            try {
                ShadersFixerLateMixins.handleInterpolation(interp);
            } catch (NoClassDefFoundError ignored) {} // INTERPOLATE FOV (SCOPE)
        }
    }

    // Vanilla / MixinHandRenderer for Angelica
    @ModifyArg(
        method = "renderHand",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/EntityRenderer;getFOVModifier(FZ)F",
            ordinal = 0),
        index = 1)
    private boolean FOVConfigApply(boolean useFOVSetting) {
        if (ShitUtils.shaders_fixer$checkVibe()) {
            return ShadersFixerLateMixins.getFOVConf();
        }
        return false;
    }

    // Shared, for both Vanilla / Angelica
    @ModifyConstant(method = "getFOVModifier", constant = @Constant(floatValue = 70.0F))
    public float ModifyBaseFOV(float fov, @Local EntityLivingBase entityplayer) {
        if (ShitUtils.shaders_fixer$checkVibe()) {
            fov = ShadersFixerLateMixins.getGunsBaseFOV(entityplayer.getHeldItem());
            return fov;
        }
        return fov;
    }
}
