package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import com.github.lunatrius.schematica.client.renderer.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.github.lunatrius.core.util.vector.Vector3f;

import javax.vecmath.Point2f;
import java.util.Stack;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface(Vector3f zero, Vector3f size, int sides, float red, float green, float blue, float alpha, CallbackInfo ci) {
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline(Vector3f zero, Vector3f size, int sides, float red, float green, float blue, float alpha, CallbackInfo ci) {
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

}
