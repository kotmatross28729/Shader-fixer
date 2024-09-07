package com.kotmatross.shadersfixer.mixins.late.client.oc.client;

import com.kotmatross.shadersfixer.Utils;
import li.cil.oc.client.renderer.tileentity.RobotRenderer$;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RobotRenderer$.class, priority = 999)
public class MixinRobotRenderer {
    @Inject(method = "renderTileEntityAt",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.Fix();
    }
}
