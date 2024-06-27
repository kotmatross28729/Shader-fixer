package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rivalrebels.client.renderentity.RenderRhodes;
import rivalrebels.common.entity.EntityLightningLink;
import rivalrebels.common.entity.EntityRhodes;

import static com.kotmatross.shadersfixer.utils.shaders_fix;
import static rivalrebels.client.renderentity.RenderRhodes.texture;

@Mixin(value = RenderRhodes.class, priority = 999)
public class MixinRenderRhodes {

    @Inject(method = "renderRhodes*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))

    public void renderRhodes(EntityRhodes rhodes, double x, double y, double z, float par8, float tt, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    @Inject(method = "renderRhodes*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V",
            ordinal = 1),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V",
                ordinal = 9)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V"), remap = false)

    public void renderRhodes2(EntityRhodes rhodes, double x, double y, double z, float par8, float tt, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    @Inject(method = "renderRhodes*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V",
            ordinal = 9),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
                ordinal = 9)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"), remap = false)
    public void renderRhodes2_5(EntityRhodes rhodes, double x, double y, double z, float par8, float tt, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }



}
