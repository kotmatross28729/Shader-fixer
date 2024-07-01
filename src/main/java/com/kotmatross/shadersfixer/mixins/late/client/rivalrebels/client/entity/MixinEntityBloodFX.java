package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import rivalrebels.RivalRebels;
import rivalrebels.common.entity.EntityBloodFX;
import rivalrebels.common.entity.EntityGore;

@Mixin(value = EntityBloodFX.class, priority = 999)
public class MixinEntityBloodFX extends EntityFX{
    @Shadow(remap = false)
    boolean isBlood;

    public MixinEntityBloodFX(World w, double x, double y, double z, boolean b)
    {
        this(w, x, y, z, w.rand.nextGaussian() * 0.1, w.rand.nextGaussian() * 0.1, w.rand.nextGaussian() * 0.1, b);
    }
    public MixinEntityBloodFX(World w, double x, double y, double z, double r, double g, double b, boolean bl)
    {
        super(w, x, y, z, r, g, b);

        posX = x;
        posY = y;
        posZ = z;
        motionX = r;
        motionY = g;
        motionZ = b;
        particleGravity = 0.75F;
        particleMaxAge = 20;
        isBlood = bl;
    }

    public MixinEntityBloodFX(World w, EntityGore g, boolean b)
    {
        this(w, g.posX, g.posY, g.posZ, b);
    }


    /**
     * @author kotmatross
     * @reason fix particle render issue
     */
    @Overwrite(remap = false)
    public void func_70539_a(Tessellator t, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING); //volar bebé
        Minecraft.getMinecraft().renderEngine.bindTexture(isBlood ? RivalRebels.etblood : RivalRebels.etgoo);
        float f10 = 0.1F * this.particleScale;

        float f11 = (float) (prevPosX + (posX - prevPosX) * par2 - interpPosX);
        float f12 = (float) (prevPosY + (posY - prevPosY) * par2 - interpPosY);
        float f13 = (float) (prevPosZ + (posZ - prevPosZ) * par2 - interpPosZ);
        t.startDrawingQuads();
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.setColorRGBA_F(1, 1, 1, 1);
        t.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, 1, 1);
        t.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, 1, 0);
        t.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, 0, 0);
        t.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, 0, 1);
        t.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
