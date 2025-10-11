package com.kotmatross.shaderfixer.utils;

import net.coderbot.iris.gl.program.Program;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.kotmatross.shaderfixer.ShaderFixer;
import com.kotmatross.shaderfixer.Tags;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Utils {

    // OP guys
    public static final ResourceLocation shader_fix = new ResourceLocation(
        Tags.MODID,
        "textures/shaders_workaround.png");

    public static void fix() {
        Minecraft.getMinecraft().renderEngine.bindTexture(shader_fix);
    }

    // Unused
    // public static final ResourceLocation shader_fix2 = new ResourceLocation(Tags.MODID, "textures/LightingFix.png");
    //
    // public static void fix2() {
    // Minecraft.getMinecraft().renderEngine.bindTexture(shader_fix2);
    // }

    public static int getCurrentTextureID() {
        return GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
    }

    public static void bindTextureByID(int textureID) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    public static class BrightnessUtils {

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
    }

    public static class ProgramUtils {

        public static int GLGetCurrentProgram() {
            return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        }

        // Sometimes it works, sometimes it causes more bugs
        public static void GLUseDefaultProgram() {
            if (ShaderFixer.IS_ANGELICA_PRESENT) {
                Program.unbind(); // For angelica, same glUseProgram(0), but also clears uniforms and samplers
            } else {
                GL20.glUseProgram(0);
            }
        }

        public static void GLUseProgram(int program) {
            GL20.glUseProgram(program);
        }
    }

}
