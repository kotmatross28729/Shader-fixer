package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.enableNotifications;

public class ClientHandler {
    public static final ClientHandler INSTANCE = new ClientHandler();
    private final Minecraft mc = FMLClientHandler.instance().getClient();

    public static boolean Lightsabers = true;
    public static boolean Neat = true;
    public static boolean WorldTooltips = true;
    public static boolean Minechem = true;
    public static boolean ItemPhysic = true;
    public static boolean EnviroMine  = true;
    public static boolean MatterMegadrive = true;

    public static int ticks = 50; // after some messages in chat, inspired by EFR

    //Very Hacky hacky hack
    public static boolean WasLoadedLightsabers = false;
    public static boolean WasLoadedNeat = false;
    public static boolean WasLoadedWorldTooltips = false;
    public static boolean WasLoadedMinechem = false;
    public static boolean WasLoadedItemPhysic = false;
    public static boolean WasLoadedEnviroMine = false;
    public static boolean WasLoadedMatterMegadrive = false;
    public static boolean WasLoadedEndMSG = false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if(enableNotifications) {

            if (Loader.isModLoaded("lightsabers")) {if (!Loader.instance().getIndexedModList().get("lightsabers").getVersion().contains("kotmatross edition")) {Lightsabers = false;}}
            if (Loader.isModLoaded("Neat")) {if (!Loader.instance().getIndexedModList().get("Neat").getVersion().contains("kotmatross edition")) {Neat = false;}}
            if (Loader.isModLoaded("world-tooltips")) {if (!Loader.instance().getIndexedModList().get("world-tooltips").getVersion().contains("kotmatross edition")) {WorldTooltips = false;}}
            if (Loader.isModLoaded("minechem")) {if (!Loader.instance().getIndexedModList().get("minechem").getVersion().contains("kotmatross edition")) {Minechem = false;}}
            if (Loader.isModLoaded("itemphysic")) {if (!Loader.instance().getIndexedModList().get("itemphysic").getVersion().contains("kotmatross edition")) {ItemPhysic = false;}}
            if (Loader.isModLoaded("enviromine")) {if (!Loader.instance().getIndexedModList().get("enviromine").getVersion().contains("kotmatross edition")) {EnviroMine = false;}}
            if (Loader.isModLoaded("mo")) {if (!Loader.instance().getIndexedModList().get("mo").getVersion().contains("kotmatross edition")) {MatterMegadrive = false;}}

            World world = FMLClientHandler.instance().getWorldClient();
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

            if (world == null || event.phase == TickEvent.Phase.START || mc.isGamePaused()) {
                return;
            }

                if (!Lightsabers && !WasLoadedLightsabers) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Lightsabers"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Advanced_Lightsabers-Shaders_fix/releases"));
                    player.addChatComponentMessage(text2);
                    WasLoadedLightsabers = true;
                }
            }
                if (!Neat && !WasLoadedNeat) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Neat"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Neat-shaders-fix/releases"));
                    player.addChatComponentMessage(text2);
                    WasLoadedNeat = true;
                }
            }
                if (!WorldTooltips && !WasLoadedWorldTooltips) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.WorldTooltips"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/WorldTooltipsShadersFix/releases"));
                    player.addChatComponentMessage(text2);
                        WasLoadedWorldTooltips = true;
                }
            }
                if (!Minechem && !WasLoadedMinechem) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Minechem"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Minechem-5-continuation/releases"));
                    player.addChatComponentMessage(text2);
                        WasLoadedMinechem = true;
                }
            }
                if (!ItemPhysic && !WasLoadedItemPhysic) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.ItemPhysic"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/ItemPhysic/releases"));
                    player.addChatComponentMessage(text2);
                        WasLoadedItemPhysic = true;
                }
            }
                if (!EnviroMine && !WasLoadedEnviroMine) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.EnviroMine"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/EnviroMine-continuation/releases"));
                    player.addChatComponentMessage(text2);
                        ShadersFixer.logger.fatal("ticks enviro: " + ticks);
                        WasLoadedEnviroMine = true;
                }
            }
                if (!MatterMegadrive && !WasLoadedMatterMegadrive) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.MatterMegadrive"));
                    player.addChatComponentMessage(text);

                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.fork"));
                    text2.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/MatterMegadriveFork/releases"));
                    player.addChatComponentMessage(text2);
                        WasLoadedMatterMegadrive = true;
                }
            }
                if ( (!Lightsabers || !Neat || !WorldTooltips || !Minechem || !ItemPhysic || !EnviroMine || !MatterMegadrive) && !WasLoadedEndMSG ) {
                    if (player.ticksExisted == ticks) {
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.endMSG"));
                    text.getChatStyle().setColor(EnumChatFormatting.GOLD);
                    player.addChatComponentMessage(text);
                        ticks = 50;
                        WasLoadedLightsabers = false;
                        WasLoadedNeat = false;
                        WasLoadedWorldTooltips = false;
                        WasLoadedMinechem = false;
                        WasLoadedItemPhysic = false;
                        WasLoadedEnviroMine = false;
                        WasLoadedMatterMegadrive = false;
                        WasLoadedEndMSG = true;
                }
            }
        }
        WasLoadedEndMSG = false;


    }
}
