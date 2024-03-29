package com.kotmatross.shadersfixer.mixins.late.client.Techguns.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import techguns.client.particle.EntityParticleAnimated;
import techguns.client.renderer.TGRenderHelper;
import techguns.client.renderer.tileentity.RenderTGChest;

@Mixin(value = EntityParticleAnimated.class, priority = 999)
public class MixinEntityParticleAnimated extends EntityFX {
    @Shadow
    ResourceLocation fxTexture;
    @Shadow
    int cols;
    @Shadow
    int rows;
    @Shadow
    int numSprites;
    @Shadow
    private int delay = 0;
    @Shadow
    private float maxScale;
    @Shadow
    private float minScale;
    @Shadow
    private float scaleFactor;
    @Shadow
    private boolean softScaling;
    @Shadow
    public boolean variations = false;
    @Shadow
    private int variationFrame = 0;
    @Shadow
    private int random_rot = 0;
    @Shadow
    TGRenderHelper.RenderType renderType;

    /**
    * Useless thing, needed to use "extends EntityFX"
    */
    protected MixinEntityParticleAnimated(World p_i1218_1_, double p_i1218_2_, double p_i1218_4_, double p_i1218_6_) {
        super(p_i1218_1_, p_i1218_2_, p_i1218_4_, p_i1218_6_);
    }

    /**
     * @author kotmatross
     * @reason fix lighting issue
     */
    @Overwrite(remap = false)
    public void func_70539_a(final Tessellator tesselator, final float partialTickTime, final float rotX, final float rotXZ, final float rotZ, final float rotYZ, final float rotXY) {
        if (delay > 0) {
            return;
        }
        final float prog = particleAge / (float)particleMaxAge;
        int currentFrame = 0;
        if (variations) {
            currentFrame = variationFrame;
        }
        else {
            currentFrame = (int)(numSprites * prog);
        }
        if (currentFrame < numSprites) {
            if (maxScale != 0.0f) {
                final float p = (float)(this.softScaling ? Math.sqrt(prog) : prog);
                this.particleScale = Math.min(this.minScale + (this.maxScale - this.minScale) * p * (1.0f / this.scaleFactor), this.maxScale);
            }
            tesselator.draw();
            GL11.glPushAttrib(8192);
            if (this.renderType != TGRenderHelper.RenderType.SOLID) {
                GL11.glEnable(3042);
                GL11.glEnable(3008);
            }
            if (this.renderType == TGRenderHelper.RenderType.ALPHA) {
                GL11.glBlendFunc(770, 771);
            }
            else if (this.renderType == TGRenderHelper.RenderType.ADDITIVE) {
                GL11.glBlendFunc(770, 1);
            }
            if (this.renderType != TGRenderHelper.RenderType.ALPHA_SHADED) {
                //TGRenderHelper.enableFXLighting(); //Lighting issue
            }
            Minecraft.getMinecraft().renderEngine.bindTexture(fxTexture);
            tesselator.startDrawingQuads();
            final float f6 = (this.particleTextureIndexX + this.particleTextureJitterX / 4.0f) / 16.0f;
            final float f7 = f6 + 0.015609375f;
            final float f8 = (this.particleTextureIndexY + this.particleTextureJitterY / 4.0f) / 16.0f;
            final float f9 = f8 + 0.015609375f;
            final float fscale = 0.1f * this.particleScale;
            final float fPosX = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTickTime - EntityParticleAnimated.interpPosX);
            final float fPosY = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTickTime - EntityParticleAnimated.interpPosY);
            final float fPosZ = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTickTime - EntityParticleAnimated.interpPosZ);
            final float r = fscale;
            final int col = currentFrame % this.cols;
            final int row = currentFrame / this.cols;
            final float u = 1.0f / this.cols;
            final float v = 1.0f / this.rows;
            final float U1 = col * u;
            final float V1 = row * v;
            final float U2 = (col + 1) * u;
            final float V2 = (row + 1) * v;
            float ua = 0.0f;
            float va = 0.0f;
            float ub = 0.0f;
            float vb = 0.0f;
            float uc = 0.0f;
            float vc = 0.0f;
            float ud = 0.0f;
            float vd = 0.0f;
            switch (this.random_rot) {
                case 1: {
                    ua = U1;
                    va = V2;
                    ub = U2;
                    vb = V2;
                    uc = U2;
                    vc = V1;
                    ud = U1;
                    vd = V1;
                    break;
                }
                case 2: {
                    ua = U1;
                    va = V1;
                    ub = U1;
                    vb = V2;
                    uc = U2;
                    vc = V2;
                    ud = U2;
                    vd = V1;
                    break;
                }
                case 3: {
                    ua = U2;
                    va = V1;
                    ub = U1;
                    vb = V1;
                    uc = U1;
                    vc = V2;
                    ud = U2;
                    vd = V2;
                    break;
                }
                default: {
                    ua = U2;
                    va = V2;
                    ub = U2;
                    vb = V1;
                    uc = U1;
                    vc = V1;
                    ud = U1;
                    vd = V2;
                    break;
                }
            }
            tesselator.setNormal(0.0f, 1.0f, 0.0f);
            tesselator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
            tesselator.addVertexWithUV((double)(fPosX - rotX * fscale - rotYZ * fscale), (double)(fPosY - rotXZ * fscale), (double)(fPosZ - rotZ * fscale - rotXY * fscale), (double)ua, (double)va);
            tesselator.addVertexWithUV((double)(fPosX - rotX * fscale + rotYZ * fscale), (double)(fPosY + rotXZ * fscale), (double)(fPosZ - rotZ * fscale + rotXY * fscale), (double)ub, (double)vb);
            tesselator.addVertexWithUV((double)(fPosX + rotX * fscale + rotYZ * fscale), (double)(fPosY + rotXZ * fscale), (double)(fPosZ + rotZ * fscale + rotXY * fscale), (double)uc, (double)vc);
            tesselator.addVertexWithUV((double)(fPosX + rotX * fscale - rotYZ * fscale), (double)(fPosY - rotXZ * fscale), (double)(fPosZ + rotZ * fscale - rotXY * fscale), (double)ud, (double)vd);
            tesselator.draw();
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));
            GL11.glPopAttrib();
            tesselator.startDrawingQuads();
            if (this.renderType != TGRenderHelper.RenderType.ALPHA_SHADED) {
                //TGRenderHelper.disableFXLighting();  //Lighting issue
            }
        }
    }
}
