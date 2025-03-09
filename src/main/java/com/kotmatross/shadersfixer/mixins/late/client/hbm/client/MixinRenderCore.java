package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderCore;
import com.hbm.tileentity.machine.TileEntityCore;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderCore.class, priority = 999)
public class MixinRenderCore {

    @Inject(method = "renderStandby", at = @At(value = "HEAD"), remap = false)
    public void renderStandby(double x, double y, double z, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(TileEntityCore tile, double x, double y, double z, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderFlare", at = @At(value = "HEAD"), remap = false)
    public void renderFlare(TileEntityCore core, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    public int shaders_fixer$program;

    @Inject(
        method = "renderFlare",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void renderFlarePR(TileEntityCore core, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderFlare",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderFlarePRE(TileEntityCore core, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

}
