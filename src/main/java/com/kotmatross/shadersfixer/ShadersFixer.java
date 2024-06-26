package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import com.kotmatross.shadersfixer.LightingFix.EventHandler;
import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.ForceDisableLightingFix;

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
        ShaderFixerConfig.loadLightingFixConfig(new File(Launch.minecraftHome, configFolder + "LightingFix.cfg"));
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
            if (LightingFix()) {
                logger.info("ShadersMod or Psychedelicraft loaded, lighting fix = true ");
                ShaderFixerConfig.LightingFix = true;
            } else {
                if (!SHADERS_MOD() && !isPsychedelicraftLoaded()) {
                    logger.warn("ShadersMod and Psychedelicraft is not loaded, skip lighting fix ");
                    ShaderFixerConfig.LightingFix = false;
                }
            }
        }
        if(!ForceDisableLightingFix) {
            if(ShaderFixerConfig.LightingFix) {
                EventHandler EHandler = new EventHandler();
                FMLCommonHandler.instance().bus().register(EHandler);
                MinecraftForge.EVENT_BUS.register(EHandler);
            }
        }
    }
}
