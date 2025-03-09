package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderLiquefactor;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderLiquefactor.class, priority = 999)
public class MixinRenderLiquefactor {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glScaled(DDD)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.Fix();
    }
}
