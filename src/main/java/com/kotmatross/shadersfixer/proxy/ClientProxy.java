package com.kotmatross.shadersfixer.proxy;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.enableXNotifications;

import net.minecraftforge.common.MinecraftForge;

import com.kotmatross.shadersfixer.handlers.ClientHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerEvents() {
        super.registerEvents();
        if (enableXNotifications) {
            FMLCommonHandler.instance()
                .bus()
                .register(ClientHandler.INSTANCE);
            MinecraftForge.EVENT_BUS.register(ClientHandler.INSTANCE);
        }
    }
}
