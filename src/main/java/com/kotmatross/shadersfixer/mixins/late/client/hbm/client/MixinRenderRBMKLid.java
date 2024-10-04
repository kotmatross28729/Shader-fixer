package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderRBMKLid;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderRBMKLid.class, priority = 999)
public class MixinRenderRBMKLid {

    //FUCK
//    @Unique
//    public int shaders_fixer$program;
//
//    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", ordinal = 0, shift = At.Shift.BEFORE))
//    public void func_147500_aPR(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
//        shaders_fixer$program = Utils.GLGetCurrentProgram();
//        Utils.GLUseDefaultProgram();
//    }
//    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = At.Shift.AFTER))
//    public void func_147500_aPRE(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
//        Utils.GLUseProgram(shaders_fixer$program);
//    }

    @Inject(method = "func_147500_a",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_147500_a(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
