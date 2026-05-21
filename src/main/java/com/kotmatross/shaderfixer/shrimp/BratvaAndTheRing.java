package com.kotmatross.shaderfixer.shrimp;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderItemInFrameEvent;

import org.lwjgl.opengl.GL11;

import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BratvaAndTheRing {

    private static final ResourceLocation bratva = new ResourceLocation(Tags.MODID + ":textures/bratva.png");

    /// MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public void onRenderItemInFrame(RenderItemInFrameEvent event) {
        if (event.item != null && event.item.getItem() == Items.gold_nugget) {
            event.setCanceled(true);
            if (AngelicaUtils_WRAPPER.isShadowPass()) return;

            double PIXEL = 1D / 16D;
            double OFFSET = PIXEL * 2.75D;

            GL11.glDisable(GL11.GL_LIGHTING);
            Minecraft.getMinecraft().renderEngine.bindTexture(bratva);
            Tessellator tess = Tessellator.instance;
            tess.startDrawingQuads();
            tess.setNormal(0, 1, 0);
            tess.addVertexWithUV(0.5, 0.5 + OFFSET, PIXEL * 0.5, 1, 0);
            tess.addVertexWithUV(-0.5, 0.5 + OFFSET, PIXEL * 0.5, 0, 0);
            tess.addVertexWithUV(-0.5, -0.5 + OFFSET, PIXEL * 0.5, 0, 1);
            tess.addVertexWithUV(0.5, -0.5 + OFFSET, PIXEL * 0.5, 1, 1);
            tess.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

}
