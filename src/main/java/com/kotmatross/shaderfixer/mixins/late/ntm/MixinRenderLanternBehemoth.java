package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderLanternBehemoth;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderLanternBehemoth.class, priority = 999)
public class MixinRenderLanternBehemoth {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 2,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 2,
            shift = At.Shift.AFTER),
        remap = false)
    private void func_147500_a2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
