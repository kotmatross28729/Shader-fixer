package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity.effect;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.effect.RenderGravityWave;
import com.fiskmods.heroes.common.entity.effect.EntityGravityWave;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderGravityWave.class, priority = 999)
public abstract class MixinRenderGravityWave extends Render {

    @Inject(
        method = "doRender",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void doRender(EntityGravityWave entity, double x, double y, double z, float entityYaw, float partialTicks,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
