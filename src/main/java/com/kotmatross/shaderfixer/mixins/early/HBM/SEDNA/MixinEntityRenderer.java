package com.kotmatross.shaderfixer.mixins.early.HBM.SEDNA;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;
import com.llamalad7.mixinextras.sugar.Local;

/**
 * Side NTM gun things (interpolation / fov / misc GL stuff)
 *
 * @author kotmatross
 */
@SuppressWarnings("LocalMayBeArgsOnly") // SHUT THE FUCK UP '@Local may be argsOnly = true'
@Mixin(value = EntityRenderer.class, priority = 1003)
public class MixinEntityRenderer {
    // FOR VANILLA / PARTIALLY (ANGEL)ICA (NOT FOR SHITFINE)

    // Vanilla / MixinHandRenderer for Angelica
    @Inject(method = "renderHand", at = @At(value = "HEAD"))
    public void HandleInterp(float interp, int p_78476_2_, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            NTMUtils_WRAPPER.handleInterpolation(interp); // INTERPOLATE FOV (SCOPE)
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
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            return NTMUtils_WRAPPER.getFOVConf();
        }
        return false;
    }

    // Shared, for both Vanilla / Angelica
    @ModifyConstant(method = "getFOVModifier", constant = @Constant(floatValue = 70.0F, ordinal = 0))
    public float ModifyBaseFOV(float fov, @Local EntityLivingBase entityplayer) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            return NTMUtils_WRAPPER.getGunsBaseFOV(entityplayer.getHeldItem());
        }
        return fov;
    }
}
