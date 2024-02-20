package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client;

import com.fiskmods.heroes.client.SHRenderHooks;
import com.fiskmods.heroes.common.config.SHConfig;
import com.fiskmods.heroes.util.FiskMath;
import com.fiskmods.heroes.util.SHRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = SHRenderHooks.class, priority = 999)
public class MixinSHRenderHooks {
    @Shadow
    protected static Minecraft mc = Minecraft.getMinecraft();

    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public static void drawLightningLine(Vec3 start, Vec3 end, float lineWidth, float innerLineWidth, Vec3 color, float scale, float alphaStart, float alphaEnd, boolean ignoreOld) {
        if (start != null && end != null) {
            Tessellator tessellator = Tessellator.instance;
            if (SHConfig.oldLightning.test() && !ignoreOld) {
                float prevWidth = GL11.glGetFloat(2849);
                if (lineWidth > 0.0F) {
                    GL11.glLineWidth(lineWidth);
                    tessellator.startDrawing(3);
                    Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
                    tessellator.setColorRGBA_F((float)color.xCoord, (float)color.yCoord, (float)color.zCoord, alphaStart);
                    tessellator.addVertex(start.xCoord, start.yCoord, start.zCoord);
                    tessellator.setColorRGBA_F((float)color.xCoord, (float)color.yCoord, (float)color.zCoord, alphaEnd);
                    tessellator.addVertex(end.xCoord, end.yCoord, end.zCoord);
                    tessellator.draw();
                }

                if (innerLineWidth > 0.0F) {
                    GL11.glLineWidth(innerLineWidth);
                    tessellator.startDrawing(3);
                    Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
                    tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, Math.max(alphaStart - 0.2F, 0.0F));
                    tessellator.addVertex(start.xCoord, start.yCoord, start.zCoord);
                    tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, Math.max(alphaEnd - 0.2F, 0.0F));
                    tessellator.addVertex(end.xCoord, end.yCoord, end.zCoord);
                    tessellator.draw();
                }

                GL11.glLineWidth(prevWidth);
            } else {
                float[] afloat = new float[]{(float)color.xCoord, (float)color.yCoord, (float)color.zCoord};
                double length = start.distanceTo(end);
                int ao = mc.gameSettings.ambientOcclusion;
                int layers = 6 + ao * 3;
                GL11.glPushMatrix();
                GL11.glTranslated(start.xCoord, start.yCoord, start.zCoord);
                SHRenderHelper.faceVec(start, end);

                for(int layer = 0; layer <= layers; ++layer) {
                    double size = ((double)innerLineWidth * 0.6 + (layer < layers ? (double)(lineWidth * (float)layer * (float)layer) * (0.125 / (double)layers) : 0.0)) * (double)scale;
                    double width = size / 48.0;
                    float opacityStart;
                    float opacityEnd;
                    if (layer < layers) {
                        GL11.glDepthMask(false);
                        opacityStart = alphaStart * 0.5F / (float)layers;
                        opacityEnd = alphaEnd * 0.5F / (float)layers;
                    } else {
                        GL11.glDepthMask(true);
                        opacityStart = Math.max(alphaStart - 0.2F, 0.0F);
                        opacityEnd = Math.max(alphaEnd - 0.2F, 0.0F);
                        afloat = new float[]{1.0F, 1.0F, 1.0F};
                    }

                    tessellator.startDrawingQuads();
                    Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityEnd);
                    tessellator.addVertex(width, width, length);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityStart);
                    tessellator.addVertex(width, width, 0.0);
                    tessellator.addVertex(-width, width, 0.0);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityEnd);
                    tessellator.addVertex(-width, width, length);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityStart);
                    tessellator.addVertex(-width, -width, 0.0);
                    tessellator.addVertex(width, -width, 0.0);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityEnd);
                    tessellator.addVertex(width, -width, length);
                    tessellator.addVertex(-width, -width, length);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityStart);
                    tessellator.addVertex(-width, width, 0.0);
                    tessellator.addVertex(-width, -width, 0.0);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityEnd);
                    tessellator.addVertex(-width, -width, length);
                    tessellator.addVertex(-width, width, length);
                    tessellator.addVertex(width, -width, length);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityStart);
                    tessellator.addVertex(width, -width, 0.0);
                    tessellator.addVertex(width, width, 0.0);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityEnd);
                    tessellator.addVertex(width, width, length);
                    tessellator.addVertex(width, -width, length);
                    tessellator.addVertex(width, width, length);
                    tessellator.addVertex(-width, width, length);
                    tessellator.addVertex(-width, -width, length);
                    tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], opacityStart);
                    tessellator.addVertex(-width, width, 0.0);
                    tessellator.addVertex(width, width, 0.0);
                    tessellator.addVertex(width, -width, 0.0);
                    tessellator.addVertex(-width, -width, 0.0);
                    tessellator.draw();
                }

                GL11.glPopMatrix();
            }
        }
    }
    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    private static void drawUntexturedRectInternal(float x, float y, float width, float height, float zLevel, int color, int alpha) {
        GL11.glDisable(3553);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
        tessellator.setColorRGBA_I(color, alpha);
        tessellator.addVertex((double)(x + width), (double)y, (double)zLevel);
        tessellator.addVertex((double)x, (double)y, (double)zLevel);
        tessellator.addVertex((double)x, (double)(y + height), (double)zLevel);
        tessellator.addVertex((double)(x + width), (double)(y + height), (double)zLevel);
        tessellator.draw();
        GL11.glEnable(3553);
    }
    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public static void drawLoadingSquares(float x, float y, float width, float height, float zLevel) {
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(7425);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
        tessellator.setColorRGBA_F(0.0625F, 0.0625F, 0.0625F, 0.4F);
        tessellator.addVertex((double)(x + width), (double)y, (double)zLevel);
        tessellator.addVertex((double)x, (double)y, (double)zLevel);
        tessellator.setColorRGBA_F(0.0625F, 0.0625F, 0.0625F, 0.35F);
        tessellator.addVertex((double)x, (double)(y + height), (double)zLevel);
        tessellator.addVertex((double)(x + width), (double)(y + height), (double)zLevel);
        tessellator.draw();
        GL11.glShadeModel(7424);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
        float f = (float)((double)System.currentTimeMillis() / 1600.0 % 1.0);
        float w = MathHelper.clamp_float(Math.min(width, height) / 6.0F, 1.0F, 8.0F);
        float s = w * 1.5F;
        float x1 = x + width / 2.0F;
        float y1 = y + height / 2.0F;
        f *= 4.0F;

        for(int i = 0; i < 2; f = (f + 2.0F) % 4.0F) {
                SHRenderHooks.drawUntexturedRect(x1 - (w + s) / 2.0F + s * FiskMath.curve(Math.min(f, 1.0F) - MathHelper.clamp_float(f - 2.0F, 0.0F, 1.0F)), y1 - (w + s) / 2.0F + s * FiskMath.curve(MathHelper.clamp_float(f - 1.0F, 0.0F, 1.0F) - MathHelper.clamp_float(f - 3.0F, 0.0F, 1.0F)), w, w, zLevel, -1);
            ++i;
        }

    }

}
