package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import fox.spiteful.avaritia.render.LudicrousRenderEvents;
import fox.spiteful.avaritia.render.ShaderCallback;
import fox.spiteful.avaritia.render.ShaderHelper;

// ! NOT FIX, JUST FOR TESTING
@Mixin(value = CosmicRenderShenanigans.class, priority = 999)
public class MixinCosmicRenderShenanigans {

    // TODO
    // TODO
    // TODO
    // TODO
    // TODO
    // TODO
    // TODO
    // TODO
    // TODO
    // TODO

    @Shadow
    public static final ShaderCallback shaderCallback = new ShaderCallback() {

        public void call(int shader) {
            Minecraft mc = Minecraft.getMinecraft();
            float yaw = 0.0F;
            float pitch = 0.0F;
            float scale = 1.0F;
            if (!CosmicRenderShenanigans.inventoryRender) {
                yaw = (float) ((double) (mc.thePlayer.rotationYaw * 2.0F) * Math.PI / (double) 360.0F);
                pitch = -((float) ((double) (mc.thePlayer.rotationPitch * 2.0F) * Math.PI / (double) 360.0F));
            } else {
                scale = 25.0F;
            }

            int time2 = ARBShaderObjects.glGetUniformLocationARB(shader, "time2");
            ARBShaderObjects.glUniform1fARB(time2, (float) mc.thePlayer.ticksExisted);
            int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
            ARBShaderObjects.glUniform1fARB(x, yaw);
            int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
            ARBShaderObjects.glUniform1fARB(z, pitch);
            int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
            ARBShaderObjects.glUniform3fARB(
                l,
                CosmicRenderShenanigans.lightlevel[0],
                CosmicRenderShenanigans.lightlevel[1],
                CosmicRenderShenanigans.lightlevel[2]);
            int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
            ARBShaderObjects.glUniform1fARB(lightmix, 0.2F);
            int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
            ARBShaderObjects.glUniformMatrix2ARB(uvs, false, LudicrousRenderEvents.cosmicUVs);
            int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
            ARBShaderObjects.glUniform1fARB(s, scale);
            int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
            ARBShaderObjects.glUniform1fARB(o, CosmicRenderShenanigans.cosmicOpacity);
        }
    };

    @Unique
    private static int shader_fixer$previousProgram;

    /**
     * @author TEST ONLY
     * @reason TEST ONLY
     */
    @Overwrite(remap = false)
    public static void useShader() {
        shader_fixer$previousProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        ShaderHelper.useShader(ShaderHelper.cosmicShader, shaderCallback);
    }

    /**
     * @author TEST ONLY
     * @reason TEST ONLY
     */
    @Overwrite(remap = false)
    public static void releaseShader() {
        ShaderHelper.useShader(shader_fixer$previousProgram);
    }

}
