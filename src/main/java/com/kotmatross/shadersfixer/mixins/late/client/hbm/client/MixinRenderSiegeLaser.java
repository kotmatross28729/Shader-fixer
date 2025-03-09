package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.projectile.EntitySiegeLaser;
import com.hbm.render.entity.projectile.RenderSiegeLaser;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderSiegeLaser.class, priority = 999)
public class MixinRenderSiegeLaser {

    @Inject(method = "renderDart", at = @At(value = "HEAD"), remap = false)
    private void renderDart(EntitySiegeLaser laser, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
