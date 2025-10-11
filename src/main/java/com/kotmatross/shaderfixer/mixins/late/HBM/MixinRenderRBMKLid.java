package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRBMKLid;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderRBMKLid.class, priority = 999)
public class MixinRenderRBMKLid {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void func_147500_a(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void func_147500_a2(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    // Make the effect much brighter (hard to see with Complementary Shaders)
    @ModifyConstant(method = "func_147500_a", constant = @Constant(floatValue = 0.1F), remap = false)
    public float IncreaseBrightness(float brightness) {
        return 0.4F;
    }

}
