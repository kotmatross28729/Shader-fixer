package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderBullet;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderBullet.class, priority = 999)
public class MixinRenderBullet {

    @Inject(method = "renderDart", at = @At(value = "HEAD"), remap = false)
    public void renderDart(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderDart", at = @At(value = "TAIL"), remap = false)
    public void renderDart2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(method = "renderTau", at = @At(value = "HEAD"), remap = false)
    public void renderTau(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderTau", at = @At(value = "TAIL"), remap = false)
    public void renderTau2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
