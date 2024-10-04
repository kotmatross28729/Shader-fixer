package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderSolarBoiler;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSolarBoiler.class, priority = 999)
public class MixinRenderSolarBoiler {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", ordinal = 0, shift = At.Shift.BEFORE))
    public void func_147500_aPR(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = At.Shift.AFTER))
    public void func_147500_aPRE(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "func_147500_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V", ordinal = 3, shift = At.Shift.AFTER), remap = false)
    public void func_147500_a(TileEntity te, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
