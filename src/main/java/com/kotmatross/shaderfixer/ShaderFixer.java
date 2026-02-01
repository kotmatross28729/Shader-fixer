package com.kotmatross.shaderfixer;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.proxy.CommonProxy;
import com.kotmatross.shaderfixer.shrimp.BratvaAndTheRing;
import com.kotmatross.shaderfixer.utils.BuiltInResourcePack;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = Tags.MCVERSION)
public class ShaderFixer {

    public static final Logger logger = LogManager.getLogger("SHADER_FIXER");
    public static boolean IS_ANGELICA_PRESENT = false;
    public static boolean IS_HBM_NTM_PRESENT = false;

    @SidedProxy(
        clientSide = "com.kotmatross.shaderfixer.proxy.ClientProxy",
        serverSide = "com.kotmatross.shaderfixer.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void onConstruction(FMLConstructionEvent event) {
        if (event.getSide() == Side.CLIENT) {
            if (FMLClientHandler.instance()
                .hasOptifine()) {
                logger.fatal("Detected Optifine, immediate crash");
                proxy.throwIncompatibleModException(
                    "Optifine is not supported by ShaderFixer",
                    "Delete Optifine and use Angelica instead");
            }
        }
    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        if (Loader.isModLoaded("angelica")) {
            IS_ANGELICA_PRESENT = true;
        }
        if (Loader.isModLoaded("hbm")) {
            IS_HBM_NTM_PRESENT = true;
        }

        if (event.getSide() == Side.CLIENT) {
            if (IS_HBM_NTM_PRESENT) {
                applyTextureFix(ShaderFixerConfig.NTM_TEXTURE_FIX, "NTM_FIX", "NTM_TEXTURE_FIX");
            }
            if (Loader.isModLoaded("Techguns")) {
                applyTextureFix(ShaderFixerConfig.TECHGUNS_TEXTURE_FIX, "TECHGUNS_FIX", "TECHGUNS_TEXTURE_FIX");
            }
            if (IS_ANGELICA_PRESENT && ShaderFixerConfig.ANGELICA_TE_SHADOW_OFFSET_DEBUG_MODE) {
                BratvaAndTheRing.regKeys();
                BratvaAndTheRing SenyaGanjubas = new BratvaAndTheRing();
                FMLCommonHandler.instance()
                    .bus()
                    .register(SenyaGanjubas);
                MinecraftForge.EVENT_BUS.register(SenyaGanjubas);
            }
        }
    }

    public static void applyTextureFix(boolean configValue, String name, String logName) {
        if (configValue) {
            logger.info(logName + " enabled, loading resource pack");
            try {
                BuiltInResourcePack.register(name);
            } catch (NoClassDefFoundError e) {
                logger.error(logName + " resource pack failed to apply:");
                e.printStackTrace();
            }
        } else {
            logger.info(logName + " disabled, skip resource pack loading");
        }
    }

}
