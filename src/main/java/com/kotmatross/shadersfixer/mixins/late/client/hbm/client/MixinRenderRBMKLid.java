package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderRBMKLid;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderRBMKLid.class, priority = 999)
public class MixinRenderRBMKLid {
    @Inject(method = "func_147500_a",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_147500_a(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
    
    //Make the effect much brighter (hard to see with Complementary Shaders)
    @ModifyConstant(method = "func_147500_a", constant = @Constant(floatValue = 0.1F), remap = false)
    public float IncreaseBrightness(float brightness) {
        return 0.4F;
    }
    
    
    //TODO: check if need glDepthMask disabled
    @Unique
    public int shaders_fixer$program;
    
    @Inject(method = "func_147500_a",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", shift = At.Shift.BEFORE))
    public void func_147500_aPR(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    
    @Inject(method = "func_147500_a",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", shift = At.Shift.AFTER))
    public void func_147500_aPRE(TileEntity te, double x, double y, double z, float i, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }
    
}
