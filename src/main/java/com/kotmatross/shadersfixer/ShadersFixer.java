package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import com.kotmatross.shadersfixer.handlers.EventHandler;
import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Mod(modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    acceptedMinecraftVersions = Tags.MCVERSION,
    dependencies =
        "required-after:gtnhmixins@[2.0.0,);")
public class ShadersFixer {

    @Mod.Instance(Tags.MODID)
    public static ShadersFixer instance;
    @SidedProxy(clientSide = Tags.CLIENTPROXY, serverSide = Tags.SERVERPROXY)
    public static CommonProxy proxy;

    public static Configuration config;
    public static final Logger logger = LogManager.getLogger();


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadWIPConfig(new File(Launch.minecraftHome, configFolder + "LightingFix.cfg"));


        EventHandler EHandler = new EventHandler();
        FMLCommonHandler.instance().bus().register(EHandler);
        MinecraftForge.EVENT_BUS.register(EHandler);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerEvents();
        proxy.init(this);
        if(ShaderFixerConfig.FixRivalRebelsShaders) {
            if (Loader.isModLoaded("rivalrebels")) {
                if (!Loader.instance().getIndexedModList().get("rivalrebels").getVersion().contains(" fixed")) {
                    throw new RuntimeException("You are using a version of Rival Rebels that is not compatible with ShadersFixer, please update to: https://github.com/kotmatross28729/Rival-Rebels-Mod/releases");
                }
            }
        }
    }

    public static boolean isPsychedelicraftLoaded() {
        return Loader.isModLoaded("psychedelicraft");
    }
    private static final boolean IS_SHADERS_MOD_PRESENT;
    static {
        boolean shadersModPresent = false;
        try {
            shadersModPresent = Launch.classLoader.getClassBytes("shadersmod.client.Shaders") != null;
        } catch (
            IOException ignored) {}
        IS_SHADERS_MOD_PRESENT = shadersModPresent;
    }
    @SideOnly(Side.CLIENT)
    public static boolean SHADERS_MOD() {
        return IS_SHADERS_MOD_PRESENT;
    }
    public static boolean LightingFix() {
        return SHADERS_MOD() || isPsychedelicraftLoaded();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (FMLLaunchHandler.side().isClient()) {
            if (!LightingFix()) {
                LogManager.getLogger().info("ShadersMod or Psychedelicraft loaded, lighting fix = true ");
            } else {
                if (!SHADERS_MOD() && !isPsychedelicraftLoaded()) {
                    LogManager.getLogger().warn("ShadersMod and Psychedelicraft is not loaded, skip lighting fix ");
                }
            }
        }
    }


    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {  //For integrated servers
            // Do stuff only for Single Player / integrated server
            MinecraftServer mc = FMLClientHandler.instance().getServer();
            String[] allNames = mc.getAllUsernames().clone();
            for (String allName : allNames) {
                // For 1.7.10, func_152612_a = getPlayerForUsername
                EntityPlayerMP playerS = MinecraftServer.getServer().getConfigurationManager().func_152612_a(allName); //event.player
                LogManager.getLogger().fatal("player: " + playerS);
                if(playerS != null) {
                    if(playerS.worldObj != null){
                        if (!playerS.worldObj.isRemote) {
                            if(playerS.isClientWorld()) {
                                if (playerS.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                                    UUID playerID = playerS.getUniqueID();
                                        Iterator<Map.Entry<UUID, EntityLightingFix>> iterator = EventHandler.Entities.entrySet().iterator();
                                        while (iterator.hasNext()) {
                                                Map.Entry<UUID, EntityLightingFix> entry = iterator.next();
                                                        if (entry.getKey().equals(playerID)) {
                                                            LogManager.getLogger().warn("entry.getKey().equals(playerID)");
                                                            EntityLightingFix entity = entry.getValue();
                                                            if (entity != null) {
                                                                entity.setDead();
                                                                Chunk chunk = playerS.worldObj.getChunkFromBlockCoords((int) entity.posX, (int) entity.posZ);
                                                                chunk.isModified = true;
                                                                iterator.remove();
                                                            }
                                                        }
                                                }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
