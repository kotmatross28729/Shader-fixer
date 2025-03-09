package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.render.entity.effect.RenderTorex;
import com.kotmatross.shadersfixer.ShadersFixer;

@Mixin(value = RenderTorex.class, priority = 999)
public abstract class MixinRenderTorex extends Render {

    @Inject(
        method = "tessellateCloudlet",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V",
            shift = At.Shift.AFTER))
    private void fixBrightnessCloud(Tessellator tess, double posX, double posY, double posZ,
        EntityNukeTorex.Cloudlet cloud, float interp, CallbackInfo ci) {
        if (ShadersFixer.IS_ANGELICA_PRESENT) {
            tess.setNormal(0.0F, 1.0F, 0.0F);
            tess.setBrightness(240);
        }
    }

    @Inject(
        method = "tessellateFlash",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V",
            shift = At.Shift.AFTER))
    private void fixBrightnessFlash(Tessellator tess, double posX, double posY, double posZ, float scale, float alpha,
        float interp, CallbackInfo ci) {
        if (ShadersFixer.IS_ANGELICA_PRESENT) {
            tess.setNormal(0.0F, 1.0F, 0.0F);
            tess.setBrightness(240);
        }
    }

}
