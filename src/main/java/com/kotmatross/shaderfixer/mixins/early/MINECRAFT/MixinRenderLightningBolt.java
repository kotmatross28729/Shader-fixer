package com.kotmatross.shaderfixer.mixins.early.MINECRAFT;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.entity.effect.EntityLightningBolt;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderLightningBolt.class, priority = 999)
public abstract class MixinRenderLightningBolt extends Render {

    @Inject(
        method = "doRender(Lnet/minecraft/entity/effect/EntityLightningBolt;DDDFF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void fixLightningBoltRender(EntityLightningBolt p_76986_1_, double p_76986_2_, double p_76986_4_,
        double p_76986_6_, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.Fix();
    }
}
