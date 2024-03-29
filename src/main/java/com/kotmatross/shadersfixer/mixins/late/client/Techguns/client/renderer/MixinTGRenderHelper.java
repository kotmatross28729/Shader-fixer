package com.kotmatross.shadersfixer.mixins.late.client.Techguns.client.renderer;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import techguns.client.renderer.TGRenderHelper;

@Mixin(value = TGRenderHelper.class, priority = 999)
public class MixinTGRenderHelper {

    /**
     * @author kotmatross
     * @reason best way to fix water rendering error
     */
    @Overwrite(remap = false)
    public static void enableFXLighting() {
        GL11.glPushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
    }

    /**
     * @author kotmatross
     * @reason best way to fix water rendering error
     */
    @Overwrite(remap = false)
    public static void disableFXLighting() {
        GL11.glPopMatrix();
    }
}
