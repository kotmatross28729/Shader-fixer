package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.projectile.RenderGrapplingHook;
import com.fiskmods.heroes.common.entity.projectile.AbstractEntityGrapplingHook;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderGrapplingHook.class, priority = 999)
public abstract class MixinRenderGrapplingHook extends Render {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(AbstractEntityGrapplingHook entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
