package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.model;

import net.minecraft.client.renderer.OpenGlHelper;
import rivalrebels.client.itemrenders.AstroBlasterRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.vecmath.Point2f;
import java.util.Stack;

import static com.kotmatross.shadersfixer.utils.shaders_fix;
@Mixin(value = AstroBlasterRenderer.class, priority = 999)
public class MixinAstroBlasterRenderer {
    @Inject(method = "renderItem*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 3)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci)
    {
        Stack<Point2f> lastBrightness = new Stack();
        lastBrightness.add(new Point2f(OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
