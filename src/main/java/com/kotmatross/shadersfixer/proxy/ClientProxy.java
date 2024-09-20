package com.kotmatross.shadersfixer.proxy;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.handlers.ClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerEvents() {
        super.registerEvents();
        FMLCommonHandler.instance().bus().register(ClientHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ClientHandler.INSTANCE);
    }
    @Override
    public void init(ShadersFixer Tmod)
    {
        super.init(Tmod);
        //RenderingRegistry.registerEntityRenderingHandler(EntityLightingFix.class, new RenderEntityLightingFix());
    }
}
