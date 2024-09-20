package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.shrimp.FuckingCursed;
import com.kotmatross.shadersfixer.shrimp.FuckingShit;
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
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.io.File;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.enableNotifications;

@FuckingCursed @FuckingShit
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
    public static boolean Avaritia = true;

    public static int ticks = 50; // after some messages in chat, inspired by EFR

    //Very Hacky hacky hack
    public static boolean WasLoadedLightsabers = false;
    public static boolean WasLoadedNeat = false;
    public static boolean WasLoadedWorldTooltips = false;
    public static boolean WasLoadedMinechem = false;
    public static boolean WasLoadedItemPhysic = false;
    public static boolean WasLoadedEnviroMine = false;
    public static boolean WasLoadedMatterMegadrive = false;
    public static boolean WasLoadedAvaritia = false;
    public static boolean WasLoadedEndMSG = false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if(enableNotifications) {
//My
            if (Loader.isModLoaded("lightsabers")) {if (!Loader.instance().getIndexedModList().get("lightsabers").getVersion().contains("kotmatross edition")) {Lightsabers = false;}}
            if (Loader.isModLoaded("Neat")) {if (!Loader.instance().getIndexedModList().get("Neat").getVersion().contains("kotmatross edition")) {Neat = false;}}
            if (Loader.isModLoaded("world-tooltips")) {if (!Loader.instance().getIndexedModList().get("world-tooltips").getVersion().contains("kotmatross edition")) {WorldTooltips = false;}}
            if (Loader.isModLoaded("minechem")) {if (!Loader.instance().getIndexedModList().get("minechem").getVersion().contains("kotmatross edition")) {Minechem = false;}}
            if (Loader.isModLoaded("itemphysic")) {if (!Loader.instance().getIndexedModList().get("itemphysic").getVersion().contains("kotmatross edition")) {ItemPhysic = false;}}
            if (Loader.isModLoaded("enviromine")) {if (!Loader.instance().getIndexedModList().get("enviromine").getVersion().contains("kotmatross edition")) {EnviroMine = false;}}
            if (Loader.isModLoaded("mo")) {if (!Loader.instance().getIndexedModList().get("mo").getVersion().contains("kotmatross edition")) {MatterMegadrive = false;}}
//Not my
            if (Loader.isModLoaded("Avaritia")) {
                double AvaritiaVersionCurrent = Double.parseDouble(Loader.instance().getIndexedModList().get("Avaritia").getVersion());
                double AvaritiaVersionConst = Double.parseDouble("1.13");
                if (!(AvaritiaVersionCurrent > AvaritiaVersionConst) ){Avaritia = false;}
            }

            World world = FMLClientHandler.instance().getWorldClient();
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

            if (world == null || event.phase == TickEvent.Phase.START || mc.isGamePaused()) {
                return;
            }

            ChatComponentText textSF = new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format( "kotmatross.SF") + EnumChatFormatting.RESET);
            ChatComponentText textdepr0 = new ChatComponentText(I18n.format("kotmatross.depr0"));
            ChatComponentText textdepr1 = new ChatComponentText(I18n.format("kotmatross.depr1"));
            ChatComponentText textfork = new ChatComponentText(I18n.format("kotmatross.fork"));
            textfork.getChatStyle().setColor(EnumChatFormatting.AQUA);

                if (!Lightsabers && !WasLoadedLightsabers) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Lightsabers"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Advanced_Lightsabers-Shaders_fix/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedLightsabers = true;
                }
            }
                if (!Neat && !WasLoadedNeat) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Neat"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Neat-shaders-fix/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedNeat = true;
                }
            }
                if (!WorldTooltips && !WasLoadedWorldTooltips) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.WorldTooltips"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/WorldTooltipsShadersFix/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedWorldTooltips = true;
                }
            }
                if (!Minechem && !WasLoadedMinechem) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Minechem"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/Minechem-5-continuation/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedMinechem = true;
                }
            }
                if (!ItemPhysic && !WasLoadedItemPhysic) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.ItemPhysic"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/ItemPhysic/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedItemPhysic = true;
                }
            }
                if (!EnviroMine && !WasLoadedEnviroMine) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.EnviroMine"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/EnviroMine-continuation/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedEnviroMine = true;
                }
            }
                if (!MatterMegadrive && !WasLoadedMatterMegadrive) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                    ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.MatterMegadrive"));
                    textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/kotmatross28729/MatterMegadriveFork/releases"));
                    ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                    player.addChatComponentMessage(text3);
                        WasLoadedMatterMegadrive = true;
                }
            }
                if (!Avaritia && !WasLoadedAvaritia) {
                    if (player.ticksExisted == ticks) {
                        ticks += 10;
                        ChatComponentText text = new ChatComponentText(I18n.format("kotmatross.Avaritia"));
                        textfork.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/GTNewHorizons/Avaritia/releases"));
                        ChatComponentText text3 = (ChatComponentText)textSF.appendSibling(textdepr0).appendSibling(text).appendSibling(textdepr1).appendSibling(textfork);
                        player.addChatComponentMessage(text3);
                            WasLoadedAvaritia = true;
                    }
                }
                if ( (!Lightsabers || !Neat || !WorldTooltips || !Minechem || !ItemPhysic || !EnviroMine || !MatterMegadrive | !Avaritia) && !WasLoadedEndMSG ) {
                    if (player.ticksExisted == ticks) {

                    ChatComponentText text0 = new ChatComponentText(I18n.format("kotmatross.endMSG0"));
                    ChatComponentText text1 = new ChatComponentText(I18n.format("kotmatross.endMSG1"));
                    ChatComponentText text2 = new ChatComponentText(I18n.format("kotmatross.endMSG2"));
                    ChatComponentText text3 = new ChatComponentText(I18n.format("kotmatross.endMSG3"));
/**
                    //Thanks, MC, for reset formatting on EVERY FUCKING™ new line
                    ChatComponentText text4 = new ChatComponentText
                        (
                            EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format("kotmatross.endMSG4M") +
                                 EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format("kotmatross.endMSG4C") +
                                 EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format("kotmatross.endMSG4S") +
                                 EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format("kotmatross.endMSG4X") +
                                 EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + I18n.format("kotmatross.endMSG4E") +
                                 EnumChatFormatting.RESET
                        );
*/
                        ChatComponentText text4 = new ChatComponentText
                            (
                                 I18n.format("kotmatross.endMSG4M") +
                                      I18n.format("kotmatross.endMSG4C") +
                                      I18n.format("kotmatross.endMSG4S") +
                                      I18n.format("kotmatross.endMSG4X") +
                                      I18n.format("kotmatross.endMSG4E") +
                                      EnumChatFormatting.RESET
                            );

                    ChatComponentText text5 = new ChatComponentText(I18n.format("kotmatross.endMSG5"));
                    String Config = Launch.minecraftHome + File.separator + "config" + File.separator + Tags.MODID + File.separator + "mixinsEarly.cfg";

                    text0.getChatStyle().setColor(EnumChatFormatting.GOLD);
                    text1.getChatStyle().setColor(EnumChatFormatting.AQUA);
                    text2.getChatStyle().setColor(EnumChatFormatting.GOLD);
                    text3.getChatStyle().setColor(EnumChatFormatting.GOLD);
                    text4.getChatStyle().setColor(EnumChatFormatting.RED).setBold(true).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Config));

                    //omagad
                    ChatComponentText textFinal = (ChatComponentText) text0.appendSibling(text1).appendSibling(text2).appendSibling(text3).appendSibling(text4).appendSibling(text5);

                    //Birb®
                    player.addChatComponentMessage(textFinal);
                        ticks = 50;
                        WasLoadedLightsabers = false;
                        WasLoadedNeat = false;
                        WasLoadedWorldTooltips = false;
                        WasLoadedMinechem = false;
                        WasLoadedItemPhysic = false;
                        WasLoadedEnviroMine = false;
                        WasLoadedMatterMegadrive = false;
                        WasLoadedAvaritia = false;
                        WasLoadedEndMSG = true;
                }
            }
        }
        WasLoadedEndMSG = false;
    }
}
