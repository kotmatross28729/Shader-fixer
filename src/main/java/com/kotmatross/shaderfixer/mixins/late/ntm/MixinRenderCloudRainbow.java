package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderCloudRainbow;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderCloudRainbow.class, priority = 999)
public class MixinRenderCloudRainbow {

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "render", at = @At(value = "TAIL"), remap = false)
    public void render2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
