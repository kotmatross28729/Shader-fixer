package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderMachineForceField;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderMachineForceField.class, priority = 999)
public class MixinRenderMachineForceField {

    //WE'RE SO FUCKING BACK
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "generateSphere",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    public void generateSpherePR(int l, int s, float rad, int hex, CallbackInfo ci) {
        GL11.glDepthMask(false);
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "generateSphere",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 0, shift = At.Shift.AFTER), remap = false)
    public void generateSpherePRE(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
        GL11.glDepthMask(true);
    }

    @Inject(method = "generateSphere",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void generateSphere(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    public int shaders_fixer$program2;

    @Inject(method = "generateSphere2",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    public void generateSpherePR2(int l, int s, float rad, int hex, CallbackInfo ci) {
        GL11.glDepthMask(false);
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "generateSphere2",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 0, shift = At.Shift.AFTER), remap = false)
    public void generateSpherePRE2(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
        GL11.glDepthMask(true);
    }

    @Inject(method = "generateSphere2",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void generateSphere2(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "func_147500_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V", shift = At.Shift.AFTER), remap = false)
    public void func_147500_a(TileEntity te, double x, double y, double z, float f, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.forcefield_top_tex);
    }


}
