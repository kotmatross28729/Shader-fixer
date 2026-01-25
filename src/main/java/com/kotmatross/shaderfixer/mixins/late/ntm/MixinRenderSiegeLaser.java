package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderSiegeLaser;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderSiegeLaser.class, priority = 999)
public class MixinRenderSiegeLaser {

    @Inject(method = "renderDart", at = @At(value = "HEAD"), remap = false)
    private void renderDart(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderDart", at = @At(value = "TAIL"), remap = false)
    private void renderDar2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
