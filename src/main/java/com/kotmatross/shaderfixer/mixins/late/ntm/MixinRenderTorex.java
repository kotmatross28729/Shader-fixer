package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderTorex;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = RenderTorex.class, priority = 999)
public abstract class MixinRenderTorex extends Render {

    @Inject(
        method = "tessellateCloudlet",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V",
            shift = At.Shift.AFTER))
    private void fixBrightnessCloud(CallbackInfo ci, @Local(argsOnly = true) Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
        tess.setBrightness(240);
    }

    @Inject(
        method = "tessellateFlash",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V",
            shift = At.Shift.AFTER))
    private void fixBrightnessFlash(CallbackInfo ci, @Local(argsOnly = true) Tessellator tess) {
        tess.setNormal(0.0F, 1.0F, 0.0F);
        tess.setBrightness(240);
    }

}
