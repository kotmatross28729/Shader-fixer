package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderMachineForceField;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = RenderMachineForceField.class, priority = 999)
public class MixinRenderMachineForceField {

    @WrapWithCondition(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lcom/hbm/render/tileentity/RenderMachineForceField;generateSphere(IIFI)V"),
        remap = false)
    private boolean dontCastShadow(RenderMachineForceField instance, int l, int s, float rad, int hex) {
        return !AngelicaUtils.isShadowPass();
    }

    @Inject(
        method = "generateSphere",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void generateSphere(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "generateSphere",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void generateSphere22(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(
        method = "generateSphere2",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void generateSphere2(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "generateSphere2",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void generateSphere222(int l, int s, float rad, int hex, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V", shift = At.Shift.AFTER),
        remap = false)
    public void func_147500_a(TileEntity te, double x, double y, double z, float f, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.forcefield_top_tex);
    }

}
