package com.kotmatross.shadersfixer.proxy;

import com.kotmatross.shadersfixer.ShadersFixer;

public class CommonProxy {
    public void registerEvents() {}
    public ShadersFixer mod;
    public void init(ShadersFixer Tmod)
    {
        mod = Tmod;
    }
}
