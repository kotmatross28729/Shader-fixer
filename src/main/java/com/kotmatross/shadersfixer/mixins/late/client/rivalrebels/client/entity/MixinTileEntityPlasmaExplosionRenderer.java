package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import rivalrebels.client.tileentityrender.TileEntityPlasmaExplosionRenderer;
import rivalrebels.common.tileentity.TileEntityPlasmaExplosion;

@Mixin(value = TileEntityPlasmaExplosionRenderer.class, priority = 999)
public class MixinTileEntityPlasmaExplosionRenderer {

    @Inject(
        method = "renderAModelAt",
        slice = @Slice(
            from = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 0),
            to = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 1)),
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"),
        remap = false)
    public void renderAModelAt(TileEntityPlasmaExplosion tile, double d, double d1, double d2, float f,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
