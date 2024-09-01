package com.kotmatross.shadersfixer.mixins.late.client.Zeldaswordskills.client.render.entity;

import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zeldaswordskills.client.render.entity.RenderEntityWhip;
import zeldaswordskills.entity.projectile.EntityWhip;

@Mixin(value = RenderEntityWhip.class, priority = 999)
public class MixinRenderEntityWhip {

    @Inject(method = "renderLeash",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderLeash(EntityWhip whip, double x, double y, double z, float yaw, float partialTick, CallbackInfo ci)
    {
        Utils.Fix();
    }

    @Inject(method = "renderLeash",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 1),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 1)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderLeash2(EntityWhip whip, double x, double y, double z, float yaw, float partialTick, CallbackInfo ci)
    {
        Utils.Fix();
    }
}
