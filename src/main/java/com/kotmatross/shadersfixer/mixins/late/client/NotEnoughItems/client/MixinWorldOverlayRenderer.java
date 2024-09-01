package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldOverlayRenderer.class, priority = 999)
public class MixinWorldOverlayRenderer {

    @Inject(method = "renderMobSpawnOverlay",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderMobSpawnOverlay(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderChunkBounds",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnd()V",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V"), remap = false)
    private static void renderChunkBounds(Entity entity, int intOffsetX, int intOffsetY, int intOffsetZ, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
