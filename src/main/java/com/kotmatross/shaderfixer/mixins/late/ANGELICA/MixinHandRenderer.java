package com.kotmatross.shaderfixer.mixins.late.ANGELICA;

import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnewhorizons.angelica.compat.mojang.Camera;
import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;

@Mixin(value = HandRenderer.class, priority = 999)
public class MixinHandRenderer {
    // FOR ANGELICA (Optifine can go fuck itself - ✅)

    @Inject(method = "setupGlState", at = @At(value = "HEAD"), remap = false)
    public void HandleInterp(RenderGlobal gameRenderer, Camera camera, float tickDelta, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            NTMUtils_WRAPPER.handleInterpolation(tickDelta); // INTERPOLATE FOV (SCOPE)
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
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            return NTMUtils_WRAPPER.getFOVConf();
        }
        return false;
    }
}
