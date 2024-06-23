package com.kotmatross.shadersfixer.proxy;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CommonProxy {
    public void registerEvents() {}
    public ShadersFixer mod;
    private int entityId = 0;
    public void init(ShadersFixer Tmod)
    {
        mod = Tmod;
        EntityRegistry.registerModEntity(EntityLightingFix.class, "EntityLightingFix", entityId++, Tmod, 100, 16, true);
    }
}
