package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity.effect;

import java.util.Random;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.effect.RenderEarthCrack;
import com.fiskmods.heroes.common.entity.effect.EntityEarthCrack;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderEarthCrack.class, priority = 999)
public abstract class MixinRenderEarthCrack extends Render {

    @Inject(
        method = "doRender",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void doRender(EntityEarthCrack entity, double x, double y, double z, float entityYaw, float partialTicks,
        CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(
        method = "renderLightning",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderLightning(Random rand, Random randPrev, int ticks, int layers, Vec3 src, Vec3 dst, int segments,
        float intensity, float[] color, float alpha, long seed, CallbackInfo ci) {
        Utils.fix();
    }
}
