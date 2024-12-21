package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.projectile;

import com.fiskmods.heroes.client.render.projectile.BulletProjectileRenderer;
import com.fiskmods.heroes.client.render.projectile.ProjectileRenderer;
import com.fiskmods.heroes.common.projectile.TrackingProjectile;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BulletProjectileRenderer.class, priority = 999)
public abstract class MixinBulletProjectileRenderer implements ProjectileRenderer {

    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void render(TrackingProjectile projectile, double viewX, double viewY, double viewZ, float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }
}
