package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderSmallReactor;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSmallReactor.class, priority = 999)
public class MixinRenderSmallReactor {
//    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 3, shift = At.Shift.BEFORE), remap = false)
//    public void func_147500_aPRD(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
//        GL11.glDepthMask(false);
//    }
//    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 4, shift = At.Shift.AFTER), remap = false)
//    public void func_147500_aPRED(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
//        GL11.glDepthMask(true);
//    }

    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", ordinal = 0, shift = At.Shift.BEFORE))
    public void func_147500_aPR(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        GL11.glDepthMask(false);
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = At.Shift.AFTER))
    public void func_147500_aPRE(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
        GL11.glDepthMask(true);
    }

    @Inject(method = "func_147500_a",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}