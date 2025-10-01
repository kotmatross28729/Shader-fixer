package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.projectile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.projectile.ProjectileRenderHandler;
import com.fiskmods.heroes.common.projectile.ProjectileTrail;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ProjectileRenderHandler.class, priority = 999)
public abstract class MixinProjectileRenderHandler {

    @Inject(
        method = "renderTrail",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderTrail(ProjectileTrail trail, CallbackInfo ci) {
        Utils.fix();
    }
}
