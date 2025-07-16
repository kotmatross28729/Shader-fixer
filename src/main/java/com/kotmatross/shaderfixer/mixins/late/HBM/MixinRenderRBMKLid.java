package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRBMKLid;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderRBMKLid.class, priority = 999)
public class MixinRenderRBMKLid {

    @Inject(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_147500_a(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    // Make the effect much brighter (hard to see with Complementary Shaders)
    @ModifyConstant(method = "func_147500_a", constant = @Constant(floatValue = 0.1F), remap = false)
    public float IncreaseBrightness(float brightness) {
        return 0.4F;
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void func_147500_aPR(TileEntity te, double x, double y, double z, float i, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
        GL11.glDepthMask(false);
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void func_147500_aPRE(TileEntity te, double x, double y, double z, float i, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
        GL11.glDepthMask(true);
    }

}
