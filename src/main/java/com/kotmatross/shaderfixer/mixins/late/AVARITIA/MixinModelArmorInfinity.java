package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import fox.spiteful.avaritia.render.ModelArmorInfinity;
import fox.spiteful.avaritia.render.RainbowHelper;

// ! NOT FIX, JUST FOR TESTING
@Mixin(value = ModelArmorInfinity.class, priority = 999)
public class MixinModelArmorInfinity extends ModelBiped {

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
    public static ResourceLocation eyeTex = new ResourceLocation("avaritia", "textures/models/infinity_armor_eyes.png");
    @Shadow
    public static ResourceLocation wingTex = new ResourceLocation(
        "avaritia",
        "textures/models/infinity_armor_wing.png");
    @Shadow
    public static ResourceLocation wingGlowTex = new ResourceLocation(
        "avaritia",
        "textures/models/infinity_armor_wingglow.png");

    @Shadow
    private Random randy = new Random();

    @Shadow
    private ModelArmorInfinity.Overlay overlay;
    @Shadow
    private ModelArmorInfinity.Overlay invulnOverlay;
    @Shadow
    private boolean invulnRender = true;

    @Shadow
    public boolean legs = false;

    /**
     * @author TEST ONLY
     * @reason TEST ONLY
     */
    @Overwrite
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Minecraft mc = Minecraft.getMinecraft();
        boolean isFlying = entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying
            && entity.isAirBorne;

        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
        GL11.glPolygonOffset(-1.0f, -1.0f);

        super.render(entity, f, f1, f2, f3, f4, f5);

        GL11.glColor4d(1, 1, 1, 1);

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);

        CosmicRenderShenanigans.useShader();
        CosmicRenderShenanigans.bindItemTexture();

        if (this.invulnRender) {
            GL11.glColor4d(1, 1, 1, 0.2);
            this.invulnOverlay.render(entity, f, f1, f2, f3, f4, f5); // Player overlay (S)
        }

        this.overlay.render(entity, f, f1, f2, f3, f4, f5); // Armor overlay (S)
        CosmicRenderShenanigans.releaseShader();

        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);

        mc.renderEngine.bindTexture(eyeTex);
        GL11.glDisable(GL11.GL_LIGHTING);
        mc.entityRenderer.disableLightmap(0.0);

        this.setGems();

        long time = mc.thePlayer.worldObj.getWorldTime();
        double pulse = Math.sin(time / 10.0) * 0.5 + 0.5;
        GL11.glColor4d(0.84, 1, 0.95, pulse * pulse * pulse * pulse * pulse * pulse * 0.5);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        super.render(entity, f, f1, f2, f3, f4, f5); // Light overlay
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);

        if (this.invulnRender) {
            long frame = time / 3;
            randy.setSeed(frame * 1723609l);
            float o = randy.nextFloat() * 6.0f;
            float[] col = RainbowHelper.HSVtoRGB(o, 1.0f, 1.0f);

            GL11.glColor4d(col[0], col[1], col[2], 1);
            this.setEyes();
            super.render(entity, f, f1, f2, f3, f4, f5);
        }

        if (!CosmicRenderShenanigans.inventoryRender) {
            mc.entityRenderer.enableLightmap(15.0);
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4d(1, 1, 1, 1);

        // WINGS
        if (isFlying && !CosmicRenderShenanigans.inventoryRender && !this.legs) {
            this.setWings();
            mc.renderEngine.bindTexture(wingTex);
            super.render(entity, f, f1, f2, f3, f4, f5);

            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glPolygonOffset(-1.0f, -1.0f);

            CosmicRenderShenanigans.useShader();
            CosmicRenderShenanigans.bindItemTexture();

            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            this.overlay.render(entity, f, f1, f2, f3, f4, f5);

            CosmicRenderShenanigans.releaseShader();

            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);

            mc.renderEngine.bindTexture(wingGlowTex);
            GL11.glDisable(GL11.GL_LIGHTING);
            mc.entityRenderer.disableLightmap(0.0);

            GL11.glColor4d(0.84, 1, 0.95, pulse * pulse * pulse * pulse * pulse * pulse * 0.5);

            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            super.render(entity, f, f1, f2, f3, f4, f5);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            if (!CosmicRenderShenanigans.inventoryRender) {
                mc.entityRenderer.enableLightmap(0.0);
            }
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4d(1, 1, 1, 1);
        }
    }

    @Shadow
    public void setEyes() {}

    @Shadow
    public void setGems() {}

    @Shadow
    public void setWings() {}

}
