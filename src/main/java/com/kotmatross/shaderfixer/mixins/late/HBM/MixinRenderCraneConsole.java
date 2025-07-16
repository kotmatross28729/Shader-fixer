package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderCraneConsole;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderCraneConsole.class, priority = 999)
public class MixinRenderCraneConsole {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 2,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_147500_a(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
        remap = false)
    public void func_147500_a2(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.Fix();
    }
}
