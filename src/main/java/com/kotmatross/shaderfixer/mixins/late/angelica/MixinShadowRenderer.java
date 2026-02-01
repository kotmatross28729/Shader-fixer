package com.kotmatross.shaderfixer.mixins.late.angelica;

import net.coderbot.iris.pipeline.ShadowRenderer;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.shrimp.BratvaAndTheRing;

@Mixin(value = ShadowRenderer.class, priority = 999)
public class MixinShadowRenderer {

    @Inject(
        method = "renderTileEntities",
        at = @At(
            value = "INVOKE",
            target = "Lnet/coderbot/iris/layer/GbufferPrograms;setBlockEntityDefaults()V",
            shift = At.Shift.AFTER),
        remap = false)
    private void renderTileEntities(CallbackInfo ci) {
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
        GL11.glPolygonOffset(BratvaAndTheRing.FACTOR, 1.0F);
    }

    @Inject(
        method = "renderTileEntities",
        at = @At(value = "INVOKE", target = "Lnet/coderbot/iris/layer/GbufferPrograms;endBlockEntities()V"),
        remap = false)
    private void renderTileEntities2(CallbackInfo ci) {
        GL11.glPolygonOffset(0.0f, 0.0f);
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
    }

}
