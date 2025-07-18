package com.kotmatross.shaderfixer.mixins.late.HEE;

import net.minecraft.entity.effect.EntityLightningBolt;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import chylex.hee.render.weather.RenderWeatherLightningBoltPurple;

@Mixin(value = RenderWeatherLightningBoltPurple.class, priority = 999)
public class MixinRenderWeatherLightningBoltPurple {

    @Inject(
        method = "func_76986_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void func_76986_a(EntityLightningBolt bolt, double x, double y, double z, float yaw, float partialTickTime,
        CallbackInfo ci) {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }

}
