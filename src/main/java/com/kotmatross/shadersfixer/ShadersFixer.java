package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import techguns.client.GoreData;
import techguns.client.models.ModelGibs;
import techguns.client.models.ModelGibsBiped;

import static techguns.client.DeathEffect.goreStats;

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
        if(ShaderFixerConfig.FixRivalRebelsShaders) {
            if (Loader.isModLoaded("rivalrebels")) {
                if (!Loader.instance().getIndexedModList().get("rivalrebels").getVersion().contains(" fixed")) {
                    throw new RuntimeException("You are using a version of Rival Rebels that is not compatible with ShadersFixer, please update to: https://github.com/kotmatross28729/Rival-Rebels-Mod/releases");
                }
            }
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
