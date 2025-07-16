package com.kotmatross.shaderfixer.mixins.late.RIVALREBELS.entity;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import rivalrebels.client.renderentity.RenderLightningLink;
import rivalrebels.common.entity.EntityLightningLink;

@Mixin(value = RenderLightningLink.class, priority = 999)
public class MixinRenderLightningLink {

    @Inject(
        method = "renderLightningLink*",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 3)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderLightningLink(EntityLightningLink ell, double x, double y, double z, float yaw, float pitch,
        CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity entityLightningLink, double x, double y, double z, float yaw, float pitch,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
