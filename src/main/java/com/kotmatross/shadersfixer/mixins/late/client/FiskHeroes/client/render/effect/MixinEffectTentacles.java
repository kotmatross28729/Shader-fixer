package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.effect;


import com.fiskmods.heroes.client.render.effect.Effect;
import com.fiskmods.heroes.client.render.effect.EffectTentacles;
import com.fiskmods.heroes.util.SHRenderHelper;
import com.fiskmods.heroes.util.Vectors;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Consumer;

@Mixin(value = EffectTentacles.class, priority = 999)
public abstract class MixinEffectTentacles implements Effect {

    /**
     * @author kotmatross
     * @reason yes
     */
    @Overwrite(remap = false)
    public static void renderTentacle(Consumer<Float> render, Vec3 src, Vec3 mid, Vec3 dst, float segLength, int segments) {
        segLength /= 16.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (RenderManager.debugBoundingBox) {
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(3);
            tessellator.addVertex(src.xCoord, src.yCoord, src.zCoord);
            tessellator.addVertex(mid.xCoord, mid.yCoord, mid.zCoord);
            tessellator.addVertex(dst.xCoord, dst.yCoord, dst.zCoord);
            tessellator.draw();
        }

        Vec3[] vecs = new Vec3[segments + 1];
        vecs[0] = src;

        int i;
        for(i = 1; i <= segments; ++i) {
            float f = (float)i / (float)segments;
            vecs[i] = Vectors.bez(src, mid, dst, f);
        }

        for(i = 1; i < vecs.length; ++i) {
            Vec3 v0 = vecs[i - 1];
            Vec3 v1 = vecs[i];
            GL11.glPushMatrix();
            GL11.glTranslated(v0.xCoord, v0.yCoord, v0.zCoord);
            SHRenderHelper.faceVec(v0, v1);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            float segDist = (float)v0.distanceTo(v1);
            float f = 0.0F;
            render.accept(0.0625F);

            while((f += segLength) <= segDist) {
                GL11.glTranslatef(0.0F, -Math.min(segLength, segDist - f), 0.0F);
                render.accept(0.0625F);
            }

            GL11.glPopMatrix();
        }

    }
}
