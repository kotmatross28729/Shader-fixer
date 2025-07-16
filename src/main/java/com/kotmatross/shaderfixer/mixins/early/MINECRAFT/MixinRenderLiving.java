package com.kotmatross.shaderfixer.mixins.early.MINECRAFT;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderLiving.class, priority = 999)
public abstract class MixinRenderLiving {

    @Inject(
        method = "func_110827_b",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void fixLeashRender(EntityLiving p_110827_1_, double p_110827_2_, double p_110827_4_, double p_110827_6_,
        float p_110827_8_, float p_110827_9_, CallbackInfo ci) {
        Utils.Fix();
    }

}
