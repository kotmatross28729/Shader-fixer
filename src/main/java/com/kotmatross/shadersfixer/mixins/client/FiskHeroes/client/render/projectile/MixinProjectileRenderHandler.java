package com.kotmatross.shadersfixer.mixins.client.FiskHeroes.client.render.projectile;

import com.fiskmods.heroes.client.render.projectile.ProjectileRenderHandler;
import com.fiskmods.heroes.common.projectile.ProjectileTrail;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.vecmath.Point3f;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = ProjectileRenderHandler.class, priority = 999)
public abstract class MixinProjectileRenderHandler {
    @Shadow
    @Final
    private Minecraft mc;

    @Shadow
    @Final
    private ICamera camera;

    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    private void renderTrail(ProjectileTrail trail) {
        if (this.camera.isBoundingBoxInFrustum(trail.computeRenderBounds())) {
            Tessellator tessellator = Tessellator.instance;
                int i = trail.getBrightnessForRender(this.mc.theWorld, trail.origin);
            float a = Math.max((1.0F - trail.getProgress()) * 0.5F, 0.0F);
            float gs = 0.5F;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)(i % 65536), (float)i / 65536.0F);
            tessellator.startDrawingQuads();
            Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
            tessellator.setTranslation(trail.origin.xCoord - TileEntityRendererDispatcher.staticPlayerX, trail.origin.yCoord - TileEntityRendererDispatcher.staticPlayerY, trail.origin.zCoord - TileEntityRendererDispatcher.staticPlayerZ);
            tessellator.setColorRGBA_F(gs, gs, gs, a);
            Point3f[][] var6 = trail.vertices;
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Point3f[] ap = var6[var8];
                Point3f[] var10 = ap;
                int var11 = ap.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    Point3f p = var10[var12];
                        tessellator.addVertex((double)p.x, (double)p.y, (double)p.z);
                }
            }

            tessellator.draw();
            tessellator.setTranslation(0.0, 0.0, 0.0);
        }

    }
}
