package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderLantern;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderLantern.class, priority = 999)
public class MixinRenderLantern {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 1,
            shift = At.Shift.AFTER),
        remap = false)
    private void func_147500_a2(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
