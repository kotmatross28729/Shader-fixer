package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSonicWave;
import com.fiskmods.heroes.common.entity.projectile.EntitySonicWave;
import com.fiskmods.heroes.util.SHRenderHelper;
import com.fiskmods.heroes.util.Vectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderSonicWave.class, priority = 999)
public abstract class MixinRenderSonicWave extends Render {
    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public void render(EntitySonicWave entity, double x, double y, double z, float f, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y + entity.height / 2.0F, (float)z);
        GL11.glRotatef(SHRenderHelper.interpolate(entity.rotationYaw, entity.prevRotationYaw), 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-SHRenderHelper.interpolate(entity.rotationPitch, entity.prevRotationPitch), 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, entity.getOpacity(partialTicks));
        float angleIncr = 10.0F;
        float radius = entity.getRadius(partialTicks);
        float radius1 = radius / 1.25F;
        float length = Math.max(radius / 5.0F, 0.1F);
        Vec3 prevVec = Vec3.createVectorHelper(0.0, (double)radius, 0.0);
        Vec3 prevVec1 = Vec3.createVectorHelper(0.0, (double)radius1, 0.0);

        for(int i = 0; (float)i <= 360.0F / angleIncr; ++i) {
            Vec3 vec3 = Vec3.createVectorHelper(0.0, (double)radius, 0.0);
            Vec3 vec31 = Vec3.createVectorHelper(0.0, (double)radius1, 0.0);
            float pitch = 0.0F;
            float yaw = 0.0F;
            float roll = (float)i * angleIncr;
            vec3.rotateAroundX(-pitch * 3.1415927F / 180.0F);
            vec3.rotateAroundY(-yaw * 3.1415927F / 180.0F);
            vec3.rotateAroundZ(-roll * 3.1415927F / 180.0F);
            vec31.rotateAroundX(-pitch * 3.1415927F / 180.0F);
            vec31.rotateAroundY(-yaw * 3.1415927F / 180.0F);
            vec31.rotateAroundZ(-roll * 3.1415927F / 180.0F);
            tessellator.addVertex(vec3.xCoord, vec3.yCoord, 0.0);
            tessellator.addVertex(vec3.xCoord, vec3.yCoord, (double)length);
            tessellator.addVertex(prevVec.xCoord, prevVec.yCoord, (double)length);
            tessellator.addVertex(prevVec.xCoord, prevVec.yCoord, 0.0);
            tessellator.addVertex(vec31.xCoord, vec31.yCoord, 0.0);
            tessellator.addVertex(vec3.xCoord, vec3.yCoord, 0.0);
            tessellator.addVertex(prevVec.xCoord, prevVec.yCoord, 0.0);
            tessellator.addVertex(prevVec1.xCoord, prevVec1.yCoord, 0.0);
            prevVec = Vectors.copy(vec3);
            prevVec1 = Vectors.copy(vec31);
        }

        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glAlphaFunc(516, 0.003921569F);
        GL11.glDepthMask(false);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
}
