package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;


import com.fiskmods.heroes.client.render.entity.projectile.RenderEnergyBolt;
import com.fiskmods.heroes.common.entity.projectile.EntityEnergyBolt;
import com.fiskmods.heroes.util.SHRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderEnergyBolt.class, priority = 999)
public class MixinRenderEnergyBolt {


    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public void renderBolt(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks) {
        Tessellator tessellator = Tessellator.instance;
        Vec3 src = Vec3.createVectorHelper(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
        Vec3 dst = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
        Vec3 color = entity.getColor();
        int ao = Minecraft.getMinecraft().gameSettings.ambientOcclusion;
        int layers = 10 + ao * 20;
        src = dst.subtract(src);
        dst = dst.subtract(dst);
        src = src.normalize();
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 32772);
        GL11.glAlphaFunc(516, 0.003921569F);
        SHRenderHelper.setLighting(15728880);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        SHRenderHelper.faceVec(src, dst);

        for(int i = 0; i <= layers; ++i) {
            if (i < layers) {
                GL11.glColor4d(color.xCoord, color.yCoord, color.zCoord, (double)(1.0F / (float)layers / 2.0F));
                GL11.glDepthMask(false);
            } else {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDepthMask(true);
            }

            double size = 0.325 + (i < layers ? (double)i * (2.5 / (double)layers) : 0.0);
            double d = (i < layers ? 1.0 - (double)i * (1.0 / (double)layers) : 0.0) * 0.1;
            double width = 0.0625 * size;
            double height = 0.0625 * size;
            double length = src.distanceTo(dst) + d;
            tessellator.startDrawingQuads();
            Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
            tessellator.addVertex(-width, height, length);
            tessellator.addVertex(width, height, length);
            tessellator.addVertex(width, height, -d);
            tessellator.addVertex(-width, height, -d);
            tessellator.addVertex(width, -height, -d);
            tessellator.addVertex(width, -height, length);
            tessellator.addVertex(-width, -height, length);
            tessellator.addVertex(-width, -height, -d);
            tessellator.addVertex(-width, -height, -d);
            tessellator.addVertex(-width, -height, length);
            tessellator.addVertex(-width, height, length);
            tessellator.addVertex(-width, height, -d);
            tessellator.addVertex(width, height, length);
            tessellator.addVertex(width, -height, length);
            tessellator.addVertex(width, -height, -d);
            tessellator.addVertex(width, height, -d);
            tessellator.addVertex(width, -height, length);
            tessellator.addVertex(width, height, length);
            tessellator.addVertex(-width, height, length);
            tessellator.addVertex(-width, -height, length);
            tessellator.addVertex(width, -height, -d);
            tessellator.addVertex(width, height, -d);
            tessellator.addVertex(-width, height, -d);
            tessellator.addVertex(-width, -height, -d);
            tessellator.draw();
        }

        SHRenderHelper.resetLighting();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
