package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderFOEQ;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderFOEQ.class, priority = 999)
public class MixinRenderFOEQ {

    @Inject(
        method = "doRender",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2),
        remap = false)
    public void doRender(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "doRender",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 3,
            shift = At.Shift.BEFORE),
        remap = false)
    public void doRender2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
