package com.kotmatross.shadersfixer.mixins.late.client.DragonBlockC.client;

import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import JinRyuu.DragonBC.common.Npcs.RenderDBC;

@Mixin(value = RenderDBC.class, priority = 999)
public class MixinRenderDBC {

    @Inject(
        method = "func_77033_b",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    protected void func_77033_b(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
