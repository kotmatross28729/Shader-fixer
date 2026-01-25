package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderDeathBlast;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderDeathBlast.class, priority = 999)
public class MixinRenderDeathBlast {

    @ModifyArg(
        method = "renderOrb",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V"),
        index = 3,
        remap = false)
    private double alphaFix(double alpha) {
        return alpha * 5F;
    }

    @Inject(method = "doRender", at = @At(value = "HEAD"), remap = false)
    public void func_76986_a(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "doRender", at = @At(value = "TAIL"), remap = false)
    public void func_76986_a2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
