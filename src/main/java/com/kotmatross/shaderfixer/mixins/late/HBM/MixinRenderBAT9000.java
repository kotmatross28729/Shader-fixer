package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderBAT9000;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderBAT9000.class, priority = 999)
public class MixinRenderBAT9000 {

    @Inject(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_147500_a(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.Fix();
    }
}
