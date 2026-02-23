package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderBeam;
import com.hbm.render.entity.projectile.RenderBeam5;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = { RenderBeam.class, RenderBeam5.class, }, priority = 999)
public class MixinRenderBeam {

    @Inject(method = "doRender", at = @At(value = "HEAD"))
    public void doRender(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "doRender", at = @At(value = "TAIL"))
    public void doRender_E(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
