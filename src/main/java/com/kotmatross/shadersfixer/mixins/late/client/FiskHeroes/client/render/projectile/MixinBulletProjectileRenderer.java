package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.projectile;

import com.fiskmods.heroes.client.render.projectile.BulletProjectileRenderer;
import com.fiskmods.heroes.client.render.projectile.ProjectileRenderer;
import com.fiskmods.heroes.common.projectile.TrackingProjectile;
import com.fiskmods.heroes.util.SHRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = BulletProjectileRenderer.class, priority = 999)
public abstract class MixinBulletProjectileRenderer implements ProjectileRenderer {


    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public void render(TrackingProjectile projectile, double viewX, double viewY, double viewZ, float partialTicks) {
        if (projectile.clientData != null) {
            Tessellator tessellator = Tessellator.instance;
            Vec3 tail = projectile.ray.getOffsetClientPosition((double)(-projectile.ray.getTrailLength()));
            Vec3 head = projectile.ray.getOffsetClientPosition(0.0);
            GL11.glEnable(3042);
            GL11.glDisable(2896);
            GL11.glDisable(3553);
            GL11.glDisable(2884);
            GL11.glDisable(3008);
            GL11.glShadeModel(7425);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            int sides = 6;
            double angle = (double)(360.0F / (float)sides);
            double l = tail.distanceTo(head);
            double w = 0.1;
            float fade = (float)projectile.getTrailFade();
            float[] afloat = (float[])((float[])projectile.clientData);
            GL11.glPushMatrix();
            GL11.glTranslated(head.xCoord - viewX, head.yCoord - viewY, head.zCoord - viewZ);
            SHRenderHelper.faceVec(head, tail);
            tessellator.startDrawing(6);
            Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);

            for(int j = 0; j < sides; ++j) {
                float rad = (float)Math.toRadians(angle * (double)j);
                float rad1 = (float)Math.toRadians(angle * (double)(j + 1));
                tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], 0.6F * fade);
                tessellator.addVertex(0.0, 0.0, 0.0);
                tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], 0.0F);
                tessellator.addVertex((double) MathHelper.sin(rad) * w, (double)MathHelper.cos(rad) * w, l);
                tessellator.addVertex((double)MathHelper.sin(rad1) * w, (double)MathHelper.cos(rad1) * w, l);
            }

            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glShadeModel(7424);
            GL11.glEnable(3008);
            GL11.glEnable(2884);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
        }
    }
}
