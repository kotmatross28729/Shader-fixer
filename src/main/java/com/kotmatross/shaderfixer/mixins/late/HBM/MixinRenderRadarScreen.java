package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.inventory.gui.GUIMachineRadarNT;
import com.hbm.render.tileentity.RenderRadarScreen;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderRadarScreen.class, priority = 999)
public class MixinRenderRadarScreen {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/renderer/Tessellator.func_78382_b ()V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/renderer/Tessellator.func_78381_a()I",
            ordinal = 0,
            shift = At.Shift.AFTER),
        remap = false)
    public void func_147500_a2(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(GUIMachineRadarNT.texture);
    }
}
