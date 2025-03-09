package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.effect;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.effect.RenderSpeedBlur;
import com.fiskmods.heroes.common.entity.effect.EntitySpeedBlur;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderSpeedBlur.class, priority = 999)
public abstract class MixinRenderSpeedBlur extends Render {

    @Inject(method = "doRender", at = @At(value = "HEAD"), remap = false)
    public void doRender(EntitySpeedBlur entity, double x, double y, double z, float entityYaw, float partialTicks,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
