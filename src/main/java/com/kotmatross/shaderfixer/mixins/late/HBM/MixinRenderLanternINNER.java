package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(targets = "com.hbm.render.tileentity.RenderLantern$1")
public class MixinRenderLanternINNER {

    // BYTECODE FUCK
    @Inject(
        method = "renderCommon",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
