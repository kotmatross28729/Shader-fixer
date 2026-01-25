package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRadGen;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderRadGen.class, priority = 999)
public class MixinRenderRadGen {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 2,
            shift = At.Shift.AFTER),
        remap = false)
    public void renderTileEntityAt(CallbackInfo ci) {
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
    public void renderTileEntityAt2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
