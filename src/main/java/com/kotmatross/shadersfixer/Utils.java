package com.kotmatross.shadersfixer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class Utils {
    //OP guys
    public static final ResourceLocation shaders_fix = new ResourceLocation(Tags.MODID, "textures/shaders_workaround.png");
    public static void Fix() {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    //numbers
    public static int INT_2X16 = 65536;
    public static int INT_MAX = 2147483647;

    //LIGHT
    public static int MAX_LIGHT_COORD = 15728880;
    public static float GetLastBrightnessX() {
        return OpenGlHelper.lastBrightnessX;
    }
    public static float GetLastBrightnessY() {
        return OpenGlHelper.lastBrightnessY;
    }
    public static void DisableFullBrightness(float lbx, float lby) {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lbx, lby);
    }
    public static void EnableFullBrightness() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)15728880 % 65536.0F, (float)15728880 / 65536.0F);
    }




}
