package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderBeam3;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderBeam3.class, priority = 999)
public class MixinRenderBeam3 {

    @Inject(
        method = "func_76986_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_76986_a(Entity rocket, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
