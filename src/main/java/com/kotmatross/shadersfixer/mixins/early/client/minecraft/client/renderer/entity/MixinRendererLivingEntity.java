package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RendererLivingEntity.class, priority = 999)
public abstract class MixinRendererLivingEntity extends Render {

    // Nametag rendering on mobs (actually no)
    @Inject(
        method = "passSpecialRender",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    protected void passSpecialRender(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_,
        double p_77033_6_, CallbackInfo ci) {
        Utils.Fix();
    }
}
