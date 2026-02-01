package com.kotmatross.shaderfixer.shrimp;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderItemInFrameEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.gtnhlib.GTNHLib;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class BratvaAndTheRing {

    public static float FACTOR = ShaderFixerConfig.ANGELICA_TE_SHADOW_OFFSET_FACTOR;

    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !--- CFG -> DEBUG MODE ---!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\
    // !-------------------------!\\

    private static final KeyBinding IncFac = new KeyBinding(
        "Increment factor",
        Keyboard.KEY_I,
        "Братва И Кольцо: glPolygonOffset наносит ответный удар");
    private static final KeyBinding DecFac = new KeyBinding(
        "Decrement factor",
        Keyboard.KEY_K,
        "Братва И Кольцо: glPolygonOffset наносит ответный удар");

    public static void regKeys() {
        ClientRegistry.registerKeyBinding(IncFac);
        ClientRegistry.registerKeyBinding(DecFac);
    }

    /// FMLCommonHandler.instance().bus()
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!(event.player instanceof EntityPlayerMP entityPlayerMP)) return;

        if (IncFac.isPressed()) {
            FACTOR++;
        } else if (DecFac.isPressed()) {
            FACTOR--;
        }

        updateDebugText(entityPlayerMP);
    }

    private void updateDebugText(EntityPlayerMP mpPlayer) {
        String colorFac = IncFac.getIsKeyPressed() ? EnumChatFormatting.GREEN.toString()
            : DecFac.getIsKeyPressed() ? EnumChatFormatting.RED.toString() : EnumChatFormatting.WHITE.toString();
        String TAXt = String.format("§6§lSHADOW OFFSET | %sFACTOR: %f", colorFac, FACTOR);
        GTNHLib.proxy.sendMessageAboveHotbar(mpPlayer, new ChatComponentText(TAXt), 40, true, true);
    }

    private static final ResourceLocation bratva = new ResourceLocation(Tags.MODID + ":textures/bratva.png");

    /// MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public void onRenderItemInFrame(RenderItemInFrameEvent event) {
        if (event.item != null && event.item.getItem() == Items.gold_nugget) {
            event.setCanceled(true);
            if (AngelicaUtils.isShadowPass()) return;

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
