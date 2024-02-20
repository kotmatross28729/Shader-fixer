package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    acceptedMinecraftVersions = Tags.MCVERSION,
    dependencies =
        "required-after:gtnhmixins@[2.0.0,);" +
        "required-after:fiskheroes@[2.3.1,);")
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
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
}
