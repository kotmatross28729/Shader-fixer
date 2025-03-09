package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderLanternBehemoth;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderLanternBehemoth.class, priority = 999)
public class MixinRenderLanternBehemoth {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 2,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
