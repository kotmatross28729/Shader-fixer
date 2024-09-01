package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rivalrebels.client.renderentity.RenderLightningBolt2;
import rivalrebels.common.entity.EntityLightningBolt2;

@Mixin(value = RenderLightningBolt2.class, priority = 999)
public class MixinRenderLightningBolt2 {
    @Inject(method = "renderLightningBolt2*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void renderLightningBolt2(EntityLightningBolt2 par1EntityLightningBolt2, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci)
    {
        Utils.Fix();
    }

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci)
    {
        Utils.Fix();
    }
}
