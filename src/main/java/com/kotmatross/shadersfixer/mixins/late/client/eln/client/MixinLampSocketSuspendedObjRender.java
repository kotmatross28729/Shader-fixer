package com.kotmatross.shadersfixer.mixins.late.client.eln.client;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import mods.eln.Eln;
import mods.eln.misc.LRDU;
import mods.eln.misc.Obj3D;
import mods.eln.misc.UtilsClient;
import mods.eln.sixnode.lampsocket.LampSocketSuspendedObjRender;

@Mixin(value = LampSocketSuspendedObjRender.class, priority = 999)
public class MixinLampSocketSuspendedObjRender {

    @Shadow(remap = false)
    private Obj3D.Obj3DPart socket;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart chain;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart base;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart lightAlphaPlaneNoDepth;
    @Shadow(remap = false)
    ResourceLocation tOn;
    @Shadow(remap = false)
    ResourceLocation tOff;
    @Shadow(remap = false)
    private boolean onOffModel;
    @Shadow(remap = false)
    private int length;
    @Shadow(remap = false)
    private boolean canSwing = true;
    @Shadow(remap = false)
    float baseLength;
    @Shadow(remap = false)
    float chainLength;

    /**
     * @author kotmatross
     * @reason Fix BSL shaders alpha issue
     */
    @Overwrite(remap = false)
    public void draw(LRDU front, float alphaZ, byte light, float pertuPy, float pertuPz, double distanceToPlayer) {
        // front.glRotateOnX();
        pertuPy /= length;
        pertuPz /= length;

        base.draw();

        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef(baseLength, 0, 0);

        for (int idx = 0; idx < length; idx++) {
            if (canSwing && Eln.allowSwingingLamps) {
                GL11.glRotatef(pertuPy, 0, 1, 0);
                GL11.glRotatef(pertuPz, 0, 0, 1);
            }
            chain.draw();
            GL11.glTranslatef(chainLength, 0, 0);
        }
        if (canSwing && Eln.allowSwingingLamps) {
            GL11.glRotatef(pertuPy, 0, 1, 0);
            GL11.glRotatef(pertuPz, 0, 0, 1);
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        if (!onOffModel) {
            socket.draw();
        } else {
            if (light > 8) {
                float l = (light) / 14f;
                GL11.glColor3f(l, l, l);

                UtilsClient.bindTexture(tOn);
            } else UtilsClient.bindTexture(tOff);
            socket.drawNoBind();

            if (light > 8) {
                UtilsClient.disableLight();

            }

            if (socket != null) socket.drawNoBind();

            if (light > 8) {
                UtilsClient.enableLight();
                GL11.glColor3f(1f, 1f, 1f);
            }
        }

        GL11.glDisable(GL11.GL_CULL_FACE);

        if (lightAlphaPlaneNoDepth != null) {
            // Beautiful effect, but overlay the whole render (i.e. through wall) : so distance limited.
            float coeff = /* 1.5f */2.0f - (float) distanceToPlayer;
            if (coeff > 0.0f && light > 0) {
                UtilsClient.enableBlend();
                UtilsClient.disableLight();
                UtilsClient.disableDepthTest();

                if ((light * 0.06667f * coeff) < 0.1) // ~0.1 - bsl "dead alpha" start
                {
                    GL11.glColor4f(1.f, 1.f, 1.f, 0.1f);
                } else {
                    GL11.glColor4f(1.f, 1.f, 1.f, light * 0.06667f * coeff);
                }

                lightAlphaPlaneNoDepth.draw();

                UtilsClient.enableDepthTest();
                UtilsClient.enableLight();
                UtilsClient.disableBlend();
            }
        }
    }
}
