package com.kotmatross.shadersfixer.mixins.client.FiskHeroes.client.render.entity;

import com.fiskmods.heroes.client.render.entity.RenderGrappleRope;
import com.fiskmods.heroes.common.entity.EntityGrappleRope;
import com.fiskmods.heroes.util.SHRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderGrappleRope.class, priority = 999)
public abstract class MixinRenderGrappleRope extends Render {

    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public void renderCable(EntityGrappleRope cable, double x, double y, double z, float f, float partialTicks) {
        GL11.glPushMatrix();
        Tessellator tessellator = Tessellator.instance;
        EntityPlayer player = cable.player;
        EntityLivingBase entity = cable.entity;
        if (cable.isEntityAlive()) {
            float f9 = player.getSwingProgress(partialTicks);
            float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.1415927F);
            Vec3 vec3 = Vec3.createVectorHelper(-0.35, -0.1, 0.35);
            vec3.rotateAroundX(-SHRenderHelper.interpolate(player.rotationPitch, player.prevRotationPitch) * 3.1415927F / 180.0F);
            vec3.rotateAroundY(-SHRenderHelper.interpolate(player.rotationYaw, player.prevRotationYaw) * 3.1415927F / 180.0F);
            vec3.rotateAroundY(f10 * 0.5F);
            double entityPosX = SHRenderHelper.interpolate(entity.posX, entity.prevPosX);
            double entityPosY = SHRenderHelper.interpolate(entity.posY, entity.prevPosY) + (double)(entity.height / 2.0F) - (double)(entity == Minecraft.getMinecraft().thePlayer ? 1.62F : 0.0F);
            double entityPosZ = SHRenderHelper.interpolate(entity.posZ, entity.prevPosZ);
            double playerPosX = SHRenderHelper.interpolate(player.posX, player.prevPosX) + vec3.xCoord;
            double playerPosY = SHRenderHelper.interpolate(player.posY, player.prevPosY) + vec3.yCoord;
            double playerPosZ = SHRenderHelper.interpolate(player.posZ, player.prevPosZ) + vec3.zCoord;
            if (this.renderManager.options.thirdPersonView > 0 || player != Minecraft.getMinecraft().thePlayer) {
                float renderYawOffset = SHRenderHelper.interpolate(player.renderYawOffset, player.prevRenderYawOffset) * 3.1415927F / 180.0F;
                double side = 0.3;
                double forward = 0.3;
                double yOffset = (player == Minecraft.getMinecraft().thePlayer ? 0.0 : (double)player.getEyeHeight()) - 0.3;
                double d = (double)MathHelper.sin(renderYawOffset);
                double d1 = (double)MathHelper.cos(renderYawOffset);
                playerPosX = SHRenderHelper.interpolate(player.posX, player.prevPosX) - d1 * side - d * forward;
                playerPosY = SHRenderHelper.interpolate(player.posY, player.prevPosY) + yOffset - 0.45;
                playerPosZ = SHRenderHelper.interpolate(player.posZ, player.prevPosZ) - d * side + d1 * forward;
            }

            Vec3 src = Vec3.createVectorHelper(entityPosX, entityPosY, entityPosZ);
            Vec3 dst = Vec3.createVectorHelper(playerPosX, playerPosY, playerPosZ);
            double width = 0.02083333395421505;
            double length = src.distanceTo(dst);
            byte segments = 24;
            x = entityPosX - RenderManager.renderPosX;
            y = entityPosY - RenderManager.renderPosY;
            z = entityPosZ - RenderManager.renderPosZ;
            GL11.glTranslated(x, y, z);
            SHRenderHelper.faceVec(src, dst);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            Vec3 vecColor1 = SHRenderHelper.getColorFromHex(cable.primaryColor);
            Vec3 vecColor2 = SHRenderHelper.getColorFromHex(cable.secondaryColor);

            for(int i = 0; i < segments; ++i) {
                double segmentLength = length / (double)segments;
                double start = (double)i * segmentLength;
                double end = (double)i * segmentLength + segmentLength;
                tessellator.startDrawingQuads();
                Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
                if (i % 2 == 0) {
                    tessellator.setColorRGBA_F((float)vecColor1.xCoord, (float)vecColor1.yCoord, (float)vecColor1.zCoord, 1.0F);
                } else {
                    tessellator.setColorRGBA_F((float)vecColor2.xCoord, (float)vecColor2.yCoord, (float)vecColor2.zCoord, 1.0F);
                }

                tessellator.addVertex(width, width, end);
                tessellator.addVertex(width, width, start);
                tessellator.addVertex(-width, width, start);
                tessellator.addVertex(-width, width, end);
                tessellator.addVertex(-width, -width, start);
                tessellator.addVertex(width, -width, start);
                tessellator.addVertex(width, -width, end);
                tessellator.addVertex(-width, -width, end);
                tessellator.addVertex(-width, width, start);
                tessellator.addVertex(-width, -width, start);
                tessellator.addVertex(-width, -width, end);
                tessellator.addVertex(-width, width, end);
                tessellator.addVertex(width, -width, end);
                tessellator.addVertex(width, -width, start);
                tessellator.addVertex(width, width, start);
                tessellator.addVertex(width, width, end);
                if (i == segments - 1) {
                    tessellator.addVertex(width, -width, end);
                    tessellator.addVertex(width, width, end);
                    tessellator.addVertex(-width, width, end);
                    tessellator.addVertex(-width, -width, end);
                } else if (i == 0) {
                    tessellator.addVertex(-width, width, start);
                    tessellator.addVertex(width, width, start);
                    tessellator.addVertex(width, -width, start);
                    tessellator.addVertex(-width, -width, start);
                }

                tessellator.draw();
            }

            GL11.glEnable(3553);
            GL11.glEnable(2896);
        }

        GL11.glPopMatrix();
    }

}
