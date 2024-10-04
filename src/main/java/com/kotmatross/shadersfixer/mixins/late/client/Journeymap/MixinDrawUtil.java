package com.kotmatross.shadersfixer.mixins.late.client.Journeymap;

import com.kotmatross.shadersfixer.Utils;
import journeymap.client.cartography.RGB;
import journeymap.client.forge.helper.IRenderHelper;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureImpl;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static journeymap.client.render.draw.DrawUtil.zLevel;

@Mixin(value = DrawUtil.class, priority = 999)
public class MixinDrawUtil {

    @Inject(method = "drawRectangle", at = @At(value = "HEAD"), remap = false)
    private static void drawRectangle(double x, double y, double width, double height, int color, int alpha, CallbackInfo ci) {
        Utils.Fix();
    }


    @Shadow(remap = false)
    private static IRenderHelper renderHelper;

    /**
     * @author kotmatross
     * @reason fix alpha
     */
    @Overwrite(remap = false)
    public static void drawQuad(TextureImpl texture, double x, double y, double width, double height, double rotation, Integer color, float alpha, boolean flip, boolean blend, int glBlendSfactor, int glBlendDFactor, boolean clampTexture) {
        GL11.glPushMatrix();

        if(alpha > 1) //alpha > 1 - shaders pain
            alpha = 1;

        try {
            if (blend) {
                renderHelper.glEnableBlend();
                renderHelper.glBlendFunc(glBlendSfactor, glBlendDFactor, 1, 0);
            }

            renderHelper.glEnableTexture2D();
            renderHelper.glBindTexture(texture.func_110552_b());
            if (blend && color != null) {
                float[] c = RGB.floats(color);
                renderHelper.glColor4f(c[0], c[1], c[2], alpha);
            } else {
                renderHelper.glColor4f(1.0F, 1.0F, 1.0F, alpha);
            }

            renderHelper.glTexParameteri(3553, 10241, 9729);
            renderHelper.glTexParameteri(3553, 10240, 9729);
            int texEdgeBehavior = clampTexture ? '脯' : 10497;
            renderHelper.glTexParameteri(3553, 10242, texEdgeBehavior);
            renderHelper.glTexParameteri(3553, 10243, texEdgeBehavior);
            if (rotation != 0.0) {
                double transX = x + width / 2.0;
                double transY = y + height / 2.0;
                GL11.glTranslated(transX, transY, 0.0);
                GL11.glRotated(rotation, 0.0, 0.0, 1.0);
                GL11.glTranslated(-transX, -transY, 0.0);
            }

            int direction = flip ? -1 : 1;
            renderHelper.startDrawingQuads(false);
            renderHelper.addVertexWithUV(x, height + y, zLevel, 0.0, 1.0);
            renderHelper.addVertexWithUV(x + width, height + y, zLevel, (double)direction, 1.0);
            renderHelper.addVertexWithUV(x + width, y, zLevel, (double)direction, 0.0);
            renderHelper.addVertexWithUV(x, y, zLevel, 0.0, 0.0);
            renderHelper.draw();
            if (blend) {
                renderHelper.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                if (glBlendSfactor != 770 || glBlendDFactor != 771) {
                    renderHelper.glEnableBlend();
                    renderHelper.glBlendFunc(770, 771, 1, 0);
                }
            }
        } finally {
            GL11.glPopMatrix();
        }

    }


}
