package com.kotmatross.shadersfixer.mixins.late.client.oc.client;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import li.cil.oc.client.renderer.tileentity.RobotRenderer$;

@Mixin(value = RobotRenderer$.class, priority = 999)
public class MixinRobotRenderer {

    @Inject(
        method = "renderTileEntityAt",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.Fix();
    }
}
