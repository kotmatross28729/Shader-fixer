package com.kotmatross.shaderfixer;

import java.io.IOException;

import net.minecraft.launchwrapper.Launch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.utils.BuiltInResourcePack;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = Tags.MCVERSION)
public class ShaderFixer {

    public static final Logger logger = LogManager.getLogger("SHADER_FIXER");
    public static boolean IS_ANGELICA_PRESENT = false;
    public static boolean IS_HBM_NTM_PRESENT = false;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        if (Loader.isModLoaded("angelica")) {
            IS_ANGELICA_PRESENT = true;
        }
        if (Loader.isModLoaded("hbm")) {
            IS_HBM_NTM_PRESENT = true;
        }

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

            if (ShaderFixerConfig.NTM_SPACE_OPTIFINE_CRASH) {

                boolean specjork;
                try {
                    specjork = (Launch.classLoader.getClassBytes("com.hbm.dim.SolarSystem") != null);
                } catch (IOException e) {
                    specjork = false;
                }

                if (specjork && FMLClientHandler.instance()
                    .hasOptifine()) {
                    logger.fatal("Detected Optifine with NTM:Space, immediate crash");
                    throw new RuntimeException(
                        "Please do NOT use Optifine with Hbm's NTM:Space. "
                            + "Some bugs can be EXTREMELY intense, and may even cause seizures. "
                            + "Use Angelica for shaders instead. "
                            + "You can disable this crash in `.minecraft\\config\\shaderfixer\\mixinsEarly_TWEAKS.cfg | NTM_SPACE_OPTIFINE_CRASH=false.` "
                            + "Only turn this off if you are ABSOLUTELY SURE about what you are doing.");
                }
            }

            if (IS_HBM_NTM_PRESENT) {
                if (ShaderFixerConfig.NTM_TEXTURE_FIX) {
                    logger.info("NTM_TEXTURE_FIX enabled, loading resource pack");
                    BuiltInResourcePack.register("NTM_FIX");
                } else {
                    logger.info("NTM_TEXTURE_FIX disabled, skip resource pack loading");
                }
            }
        }
    }

}
