package com.kotmatross.shaderfixer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.proxy.CommonProxy;
import com.kotmatross.shaderfixer.utils.BuiltInResourcePack;

import cpw.mods.fml.client.FMLClientHandler;
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
        if (Loader.isModLoaded("rivalrebels")) {
            if (!Loader.instance()
                .getIndexedModList()
                .get("rivalrebels")
                .getVersion()
                .contains(" fixed")) {
                throw new RuntimeException(
                    "You are using a version of Rival Rebels that is not compatible with ShaderFixer, please update to: https://github.com/kotmatross28729/Rival-Rebels-Mod/releases");
            }
        }
        if (event.getSide() == Side.CLIENT) {
            if (ShaderFixerConfig.OPTIFINE_CRASH) {
                if (FMLClientHandler.instance()
                    .hasOptifine()) {
                    logger.fatal("Detected Optifine, immediate crash");
                    proxy.throwIncompatibleModException(
                        "Optifine is not supported by ShaderFixer",
                        "Delete Optifine and use Angelica instead");
                }
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
                if (ShaderFixerConfig.NTM_TEXTURE_FIX) {
                    logger.info("NTM_TEXTURE_FIX enabled, loading resource pack");

                    try {
                        BuiltInResourcePack.register("NTM_FIX");
                    } catch (NoClassDefFoundError e) {
                        logger.error("NTM_TEXTURE_FIX resource pack failed to apply:");
                        e.printStackTrace();
                    }

                } else {
                    logger.info("NTM_TEXTURE_FIX disabled, skip resource pack loading");
                }
            }
        }
    }

}
