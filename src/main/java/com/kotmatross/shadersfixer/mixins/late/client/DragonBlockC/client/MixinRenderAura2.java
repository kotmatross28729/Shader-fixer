package com.kotmatross.shadersfixer.mixins.late.client.DragonBlockC.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import JinRyuu.DragonBC.common.Npcs.RenderAura2;
import JinRyuu.DragonBC.common.Npcs.EntityAura2;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.vecmath.Point2f;
import java.util.Stack;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderAura2.class, priority = 999)
public class MixinRenderAura2 {
    @Inject(method = "lightning",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private void lightning(EntityAura2 e, double par2, double par4, double par6, float par9, float var20, float var13, boolean rot, CallbackInfo ci) {
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
        Tessellator.instance.setBrightness(15728880);
    }
}
