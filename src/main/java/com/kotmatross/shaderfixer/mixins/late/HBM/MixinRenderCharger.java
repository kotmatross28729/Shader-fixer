package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderCharger;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderCharger.class, priority = 999)
public abstract class MixinRenderCharger extends TileEntitySpecialRenderer {

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/client/model/IModelCustom;renderPart(Ljava/lang/String;)V",
            ordinal = 3,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_147500_a(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(
        method = "func_147500_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/client/model/IModelCustom;renderPart(Ljava/lang/String;)V",
            ordinal = 3,
            shift = At.Shift.AFTER),
        remap = false)
    private void func_147500_a2(TileEntity tile, double x, double y, double z, float interp, CallbackInfo ci) {
        bindTexture(ResourceManager.charger_tex);
    }
}
