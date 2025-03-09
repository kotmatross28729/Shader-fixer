package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderMixer;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderMixer.class, priority = 999)
public class MixinRenderMixer {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V",
            ordinal = 2,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_76986_a(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.Fix();
    }
}
