package com.kotmatross.shadersfixer.mixins.late.client.backhand.sedna;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.shrimp.ShitUtils;

import xonin.backhand.client.utils.BackhandRenderHelper;

@Mixin(value = BackhandRenderHelper.class, priority = 999)
public class MixinBackhandRenderHelper {

    @Inject(method = "renderOffhandItemIn3rdPerson", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void renderOffhandItemIn3rdPerson(EntityPlayer player, ModelBiped modelBipedMain, float frame,
        CallbackInfo ci) {
        if (ShitUtils.shaders_fixer$checkVibe2()) {
            ci.cancel();
        }
    }

}
