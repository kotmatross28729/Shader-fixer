package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import rivalrebels.RivalRebels;
import rivalrebels.client.renderentity.RenderBlood;

@Mixin(value = RenderBlood.class, priority = 999)
public abstract class MixinRenderBlood extends Render {

    /**
     * @author kotmatross
     * @reason fix particle render issue
     */
    @Overwrite(remap = false)
    public void func_76986_a(Entity entity, double x, double y, double z, float f, float f1)
    {
        if (entity.ticksExisted > 1)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glScalef(0.25F, 0.25F, 0.25F);
            Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etblood);
            renderFaceMe();
            GL11.glPopMatrix();
        }
    }

    @Shadow
    private void renderFaceMe()
    {
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        Tessellator t = Tessellator.instance;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        t.startDrawingQuads();
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.addVertexWithUV((0.0F - var8), (0.0F - var9), 0.0D, 0, 0);
        t.addVertexWithUV((var7 - var8), (0.0F - var9), 0.0D, 1, 0);
        t.addVertexWithUV((var7 - var8), (var7 - var9), 0.0D, 1, 1);
        t.addVertexWithUV((0.0F - var8), (var7 - var9), 0.0D, 0, 1);
        t.draw();
    }

    @Shadow
    protected ResourceLocation func_110775_a(Entity entity)
    {
        return null;
    }
}
