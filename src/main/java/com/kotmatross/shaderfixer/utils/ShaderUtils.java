package com.kotmatross.shaderfixer.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.kotmatross.shaderfixer.Tags;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ShaderUtils {

    public static final ResourceLocation shader_fix = new ResourceLocation(
        Tags.MODID,
        "textures/shaders_workaround.png");

    public static void fix() {
        Minecraft.getMinecraft().renderEngine.bindTexture(shader_fix);
    }

    public static void fix(TextureManager renderEngine) {
        renderEngine.bindTexture(shader_fix);
    }

    public static int getCurrentTextureID() {
        return GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
    }

    public static void bindTextureByID(int textureID) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    public static float lbx;
    public static float lby;

    public static void enableFullBrightness() {
        lbx = OpenGlHelper.lastBrightnessX;
        lby = OpenGlHelper.lastBrightnessY;

        GL11.glPushAttrib(GL11.GL_CURRENT_BIT); // In case called before entity render
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
    }

    public static void disableFullBrightness() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lbx, lby);
        GL11.glPopAttrib();
    }

    public static int getCurrentProgram() {
        return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    public static void useDefaultProgram() {
        GL20.glUseProgram(0);
    }

    public static void useProgram(int program) {
        GL20.glUseProgram(program);
    }

}
