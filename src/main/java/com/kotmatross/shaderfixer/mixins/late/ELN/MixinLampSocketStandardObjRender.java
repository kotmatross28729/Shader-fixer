package com.kotmatross.shaderfixer.mixins.late.ELN;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import mods.eln.misc.LRDU;
import mods.eln.misc.Obj3D;
import mods.eln.misc.Utils;
import mods.eln.misc.UtilsClient;
import mods.eln.sixnode.lampsocket.LampSocketStandardObjRender;

@Mixin(value = LampSocketStandardObjRender.class, priority = 999)
public class MixinLampSocketStandardObjRender {

    @Shadow(remap = false)
    private Obj3D.Obj3DPart socket;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart socket_unlightable;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart socket_lightable;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart lampOn;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart lampOff;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart lightAlphaPlane;
    @Shadow(remap = false)
    private Obj3D.Obj3DPart lightAlphaPlaneNoDepth;
    @Shadow(remap = false)
    ResourceLocation tOn;
    @Shadow(remap = false)
    ResourceLocation tOff;
    @Shadow(remap = false)
    private boolean onOffModel;

    /**
     * @author kotmatross
     * @reason Fix BSL shaders alpha issue
     */
    @Overwrite(remap = false)
    public void draw(LRDU front, float alphaZ, byte light, boolean hasBulb, int color, double distanceToPlayer) {
        front.glRotateOnX();

        UtilsClient.disableCulling();

        Utils.setGlColorFromLamp(color);
        if (!onOffModel) {
            if (socket != null) socket.draw();
        } else {
            //
            if (light > 8) {
                UtilsClient.bindTexture(tOn);
            } else {
                UtilsClient.bindTexture(tOff);
            }
            if (socket_unlightable != null) socket_unlightable.drawNoBind();

            if (light > 8) {
                UtilsClient.disableLight();
                float l = (light) / 14f;
                // GL11.glColor3f(l, l, l);
                if (socket_lightable != null) socket_lightable.drawNoBind();
                // GL11.glColor3f(1f, 1f, 1f);
            }

            if (hasBulb) {
                if (light > 8) {
                    if (lampOn != null) lampOn.draw();
                } else {
                    if (lampOff != null) lampOff.draw();
                }
            }
            if (socket != null) socket.drawNoBind();

            if (light > 8) UtilsClient.enableLight();
            //
        }

        UtilsClient.enableBlend();
        UtilsClient.disableLight();

        if (lightAlphaPlaneNoDepth != null) {
            float coeff = /* 1.5f */2.0f - (float) distanceToPlayer;
            if (coeff > 0.0f && light > 0) {
                UtilsClient.enableCulling();
                UtilsClient.disableDepthTest(); // Beautiful effect, but overlay the whole render (i.e. through wall) :
                                                // so distance limited.

                if ((light * 0.06667f * coeff) < 0.1) // ~0.1 - bsl "dead alpha" start
                {
                    GL11.glColor4f(1.f, 1.f, 1.f, 0.1f);
                } else {
                    GL11.glColor4f(1.f, 1.f, 1.f, light * 0.06667f * coeff);
                }

                lightAlphaPlaneNoDepth.draw();
                UtilsClient.enableDepthTest();
                UtilsClient.disableCulling();
            }
        }

        if (lightAlphaPlane != null && light > 0) {
            if ((light * 0.06667f) < 0.1) // ~0.1 - bsl "dead alpha" start
            {
                GL11.glColor4f(1.f, 0.98f, 0.92f, 0.1f);
            } else {
                GL11.glColor4f(1.f, 0.98f, 0.92f, light * 0.06667f);
            }

            lightAlphaPlane.draw();
        }

        UtilsClient.enableLight();
        UtilsClient.disableBlend();

        UtilsClient.enableCulling();
        /*
         * GL11.glLineWidth(2f); GL11.glDisable(GL11.GL_TEXTURE_2D); GL11.glDisable(GL11.GL_LIGHTING);
         * GL11.glColor3f(1f,1f,1f); GL11.glBegin(GL11.GL_LINES); GL11.glVertex3d(0f, 0f, 0f);
         * GL11.glVertex3d(Math.cos(alphaZ*Math.PI/180.0), Math.sin(alphaZ*Math.PI/180.0),0.0); GL11.glEnd();
         * GL11.glEnable(GL11.GL_TEXTURE_2D); GL11.glEnable(GL11.GL_LIGHTING);
         */
    }
}
