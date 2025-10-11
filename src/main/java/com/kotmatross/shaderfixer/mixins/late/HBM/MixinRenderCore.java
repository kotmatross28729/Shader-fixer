package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderCore;
import com.hbm.tileentity.machine.TileEntityCore;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderCore.class, priority = 999)
public class MixinRenderCore {

    @Inject(method = "renderStandby", at = @At(value = "HEAD"), remap = false)
    public void renderStandby(double x, double y, double z, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderStandby", at = @At(value = "TAIL"), remap = false)
    public void renderStandby2(double x, double y, double z, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(TileEntityCore tile, double x, double y, double z, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderOrb", at = @At(value = "TAIL"), remap = false)
    public void renderOrb2(TileEntityCore tile, double x, double y, double z, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderFlare", at = @At(value = "HEAD"), remap = false)
    public void renderFlare(TileEntityCore core, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderFlare", at = @At(value = "TAIL"), remap = false)
    public void renderFlare2(TileEntityCore core, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @ModifyArg(
        method = "renderFlare",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 2F;
    }

}
