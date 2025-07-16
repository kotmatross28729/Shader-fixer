package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderRBMKConsole;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderRBMKConsole.class, priority = 999)
public class MixinRenderRBMKConsole {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_147500_a(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
