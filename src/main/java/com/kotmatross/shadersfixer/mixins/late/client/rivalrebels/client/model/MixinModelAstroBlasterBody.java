package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.model;

import net.minecraft.client.renderer.OpenGlHelper;
import rivalrebels.client.model.ModelAstroBlasterBody;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.vecmath.Point2f;
import java.util.Stack;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = ModelAstroBlasterBody.class, priority = 999)
public class MixinModelAstroBlasterBody {

    @Inject(method = "render*", at = @At(value = "HEAD"), remap = false)
    public void render(float size, float red, float green, float blue, float alpha, CallbackInfo ci)
    {
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
