package com.kotmatross.shadersfixer;

import net.coderbot.iris.gl.program.Program;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
    public static int INT_2X16 = 65_536;

    // LIGHT
    public static int MAX_LIGHT_COORD = 15_728_880;

    /*
     * Lighting docs
     * @Inject(method = "METHOD_NAME", at = @At(value = "HEAD"))
     * private void NAME(PARAMS, CallbackInfo ci, @Share("shaders_fixer$lbx") LocalFloatRef
     * shaders_fixer$lbx, @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
     * shaders_fixer$lbx.set(Utils.GetLastBrightnessX());
     * shaders_fixer$lby.set(Utils.GetLastBrightnessY());
     * }
     * =================================================================================================================
     * ================
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...", ordinal = 0, shift = BEFORE))
     * private void beforeUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci, @Share("shaders_fixer$lbx")
     * LocalFloatRef shaders_fixer$lbx, @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
     * shaders_fixer$lbx.set(Utils.GetLastBrightnessX());
     * shaders_fixer$lby.set(Utils.GetLastBrightnessY());
     * Utils.EnableFullBrightness();
     * }
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...", ordinal = 0, shift = AFTER))
     * private void afterUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci, @Share("shaders_fixer$lbx")
     * LocalFloatRef shaders_fixer$lbx, @Share("shaders_fixer$lby") LocalFloatRef shaders_fixer$lby) {
     * Utils.DisableFullBrightness(shaders_fixer$lbx.get(), shaders_fixer$lby.get());
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
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...;useShader()V", ordinal = 0, shift =
     * BEFORE), remap = false)
     * private void beforeUseShader(PARAMS, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef
     * shaders_fixer$program) {
     * shaders_fixer$program.set(Utils.GetGLCurrentProgram());
     * }
     * @Inject(method = "METHOD_NAME", at = @At(value = "INVOKE", target = "L...;releaseShader()V", ordinal = 0, shift =
     * AFTER), remap = false)
     * private void afterUseShader(PARAMS, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef
     * shaders_fixer$program) {
     * Utils.GLUseCurrentProgram(shaders_fixer$program.get());
     * }
     */
    public static int GLGetCurrentProgram() {
        return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    // Sometimes it works, sometimes it causes more bugs
    public static void GLUseDefaultProgram() {
        if (ShadersFixer.IS_ANGELICA_PRESENT) {
            Program.unbind(); // For angelica, same glUseProgram(0), but also clears uniforms and samplers
        } else {
            GL20.glUseProgram(0); // For opt#f*ne
        }
    }

    public static void GLUseProgram(int program) {
        GL20.glUseProgram(program);
    }
}
