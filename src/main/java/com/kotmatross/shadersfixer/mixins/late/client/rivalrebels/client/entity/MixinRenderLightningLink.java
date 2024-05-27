package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import assets.rivalrebels.client.renderentity.RenderLightningLink;
import assets.rivalrebels.common.entity.EntityLaserBurst;
import assets.rivalrebels.common.entity.EntityLightningLink;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderLightningLink.class, priority = 999)
public class MixinRenderLightningLink {
    @Inject(method = "renderLightningLink*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 3)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderLightningLink(EntityLightningLink ell, double x, double y, double z, float yaw, float pitch, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity entityLightningLink, double x, double y, double z, float yaw, float pitch, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
