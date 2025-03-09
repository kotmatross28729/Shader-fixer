package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import rivalrebels.client.renderentity.RenderPlasmoid;
import rivalrebels.common.entity.EntityPlasmoid;

@Mixin(value = RenderPlasmoid.class, priority = 999)

public class MixinRenderPlasmoid {

    @Inject(
        method = "renderPlasmoid",
        slice = @Slice(
            from = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 2),
            to = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 0)),
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"),
        remap = false)
    public void renderPlasmoid(EntityPlasmoid e, double x, double y, double z, float var8, float var9,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
