package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.RenderGrappleRope;
import com.fiskmods.heroes.common.entity.EntityGrappleRope;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderGrappleRope.class, priority = 999)
public abstract class MixinRenderGrappleRope extends Render {

    @Inject(
        method = "renderCable",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderCable(EntityGrappleRope cable, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci) {
        Utils.fix();
    }
}
