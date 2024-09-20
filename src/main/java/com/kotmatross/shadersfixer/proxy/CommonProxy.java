package com.kotmatross.shadersfixer.proxy;

import com.kotmatross.shadersfixer.ShadersFixer;

public class CommonProxy {
    public void registerEvents() {}
    public ShadersFixer mod;
    private int entityId = 0;
    public void init(ShadersFixer Tmod)
    {
        mod = Tmod;
        //EntityRegistry.registerModEntity(EntityLightingFix.class, "EntityLightingFix", entityId++, Tmod, ShaderFixerConfig.LightingFixRange, 16, false);
    }
}
