package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

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

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(!Utils.CanUse120GL){
            logger.error("The device doesn't support OpenGL20 (1.2), some fixes will not work!");
        }
    }

}
