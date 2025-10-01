package com.kotmatross.shaderfixer.mixins.early.MINECRAFT;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RendererLivingEntity.class, priority = 1009)
public abstract class MixinRendererLivingEntity extends Render {

    @Inject(
        method = "passSpecialRender",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    protected void fixNametagRender(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_,
        double p_77033_6_, CallbackInfo ci) {
        Utils.fix();
    }
}
