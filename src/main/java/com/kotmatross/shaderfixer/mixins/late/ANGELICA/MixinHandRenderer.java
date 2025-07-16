package com.kotmatross.shaderfixer.mixins.late.ANGELICA;

import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.renderer.RenderGlobal;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnewhorizons.angelica.compat.mojang.Camera;
import com.kotmatross.shaderfixer.utils.NTMUtils;
import com.kotmatross.shaderfixer.utils.ShitUtils;

@Mixin(value = HandRenderer.class, priority = 999)
public class MixinHandRenderer {
    // FOR ANGELICA (Optifine can go fuck itself - ✅)

    @Inject(method = "setupGlState", at = @At(value = "HEAD"), remap = false)
    public void HandleInterp(RenderGlobal gameRenderer, Camera camera, float tickDelta, CallbackInfo ci) {
        if (ShitUtils.checkVibe_FIRST_PERSON()) {
            NTMUtils.handleInterpolation(tickDelta); // INTERPOLATE FOV (SCOPE)
        }
    }

    @ModifyArg(
        method = "setupGlState",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/EntityRenderer;getFOVModifier(FZ)F",
            ordinal = 0),
        index = 1)
    private boolean FOVConfigApply(boolean useFOVSetting) {
        if (ShitUtils.checkVibe_FIRST_PERSON()) {
            return NTMUtils.getFOVConf();
        }
        return false;
    }
}
