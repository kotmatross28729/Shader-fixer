package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderCore;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderCore.class, priority = 999)
public class MixinRenderCore {

    @Inject(method = "renderStandby", at = @At(value = "HEAD"), remap = false)
    public void renderStandby(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderStandby", at = @At(value = "TAIL"), remap = false)
    public void renderStandby2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderOrb", at = @At(value = "TAIL"), remap = false)
    public void renderOrb2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(method = "renderFlare", at = @At(value = "HEAD"), remap = false)
    public void renderFlare(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderFlare", at = @At(value = "TAIL"), remap = false)
    public void renderFlare2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @ModifyArg(
        method = "renderFlare",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 2F;
    }

}
