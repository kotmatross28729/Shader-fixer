package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import codechicken.nei.WorldOverlayRenderer;

import javax.vecmath.Point2f;

import java.util.Stack;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

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
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
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
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
