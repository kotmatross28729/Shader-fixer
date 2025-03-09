package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderRadGen;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderRadGen.class, priority = 999)
public class MixinRenderRadGen {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 2,
            shift = At.Shift.AFTER),
        remap = false)
    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 2,
            shift = At.Shift.AFTER),
        remap = false)
    public void func_147500_a2(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.radgen_tex);
    }

}
