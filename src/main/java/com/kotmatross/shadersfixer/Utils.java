package com.kotmatross.shadersfixer;

import net.coderbot.iris.gl.program.Program;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Utils {

    // OP guys
    public static final ResourceLocation shaders_fix = new ResourceLocation(
        Tags.MODID,
        "textures/shaders_workaround.png");

    public static void Fix() {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    public static final ResourceLocation shaders_fix2 = new ResourceLocation(Tags.MODID, "textures/LightingFix.png");

    public static void Fix2() {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix2);
    }

    // numbers
    public static int INT_2X16 = 65536;
    public static int INT_MAX = 2147483647;

    // LIGHT
    public static int MAX_LIGHT_COORD = 15728880;

    /*
     * Lighting docs
     * @Unique public static float shaders_fixer$lbx;
     * @Unique public static float shaders_fixer$lby;
     * @Inject(method = "METHOD_NAME", at = @At(value = "HEAD"))
     * private void NAME(PARAMS, CallbackInfo ci) {
     * shaders_fixer$lbx = Utils.GetLastBrightnessX();
     * shaders_fixer$lby = Utils.GetLastBrightnessY();
     * }
     * =================================================================================================================
     * ================
     * @Unique public static float shaders_fixer$lbx;
     * @Unique public static float shaders_fixer$lby;
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...", ordinal = 0, shift = BEFORE), remap =
     * false)
     * private void beforeUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci) {
     * shaders_fixer$lbx = Utils.GetLastBrightnessX();
     * shaders_fixer$lby = Utils.GetLastBrightnessY();
     * Utils.EnableFullBrightness();
     * }
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...", ordinal = 0, shift = AFTER), remap =
     * false)
     * private void afterUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci) {
     * Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
     * }
     */
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
        OpenGlHelper.setLightmapTextureCoords(
            OpenGlHelper.lightmapTexUnit,
            (float) (MAX_LIGHT_COORD % INT_2X16),
            (float) (MAX_LIGHT_COORD / INT_2X16));
    }

    /*
     * Shader docs
     * @Unique public static int shaders_fixer$program;
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...;useShader()V", ordinal = 0, shift =
     * BEFORE), remap = false)
     * private void beforeUseShader(PARAMS, CallbackInfo ci) {
     * shaders_fixer$program = Utils.GetGLCurrentProgram();
     * }
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...;releaseShader()V", ordinal = 0, shift =
     * AFTER), remap = false)
     * private void afterUseShader(PARAMS, CallbackInfo ci) {
     * Utils.GLUseCurrentProgram(shaders_fixer$program);
     * }
     */
    public static int GLGetCurrentProgram() {
        return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    public static void GLUseDefaultProgram() {
        if (ShadersFixer.IS_ANGELICA_PRESENT) {
            Program.unbind(); // For angelica, same glUseProgram(0), but also clears uniforms and samplers
        } else {
            GL20.glUseProgram(0); // For optifine
        }
    }

    public static void GLUseProgram(int program) {
        GL20.glUseProgram(program);
    }
}
