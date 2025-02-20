package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderCore;
import com.hbm.tileentity.machine.TileEntityCore;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderCore.class, priority = 999)
public class MixinRenderCore {

    @Inject(method = "renderStandby",
        at = @At(value = "HEAD"), remap = false)
    public void renderStandby(double x, double y, double z, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderOrb",
        at = @At(value = "HEAD"), remap = false)
    public void renderOrb(TileEntityCore tile, double x, double y, double z, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    //TODO: check if need program fix
    @Inject(method = "renderFlare",
        at = @At(value = "HEAD"), remap = false)
    public void renderFlare(TileEntityCore core, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

}
