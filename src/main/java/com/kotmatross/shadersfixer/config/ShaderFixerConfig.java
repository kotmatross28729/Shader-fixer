package com.kotmatross.shadersfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {
    // Definitely does NOT look like a BugTorch config

    //Base things
    public static boolean FixFisksuperheroesShaders;

    //Category
    static final String categoryShadersfixes = "Shaders fixes";

    public static void loadMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixFisksuperheroesShaders = config.getBoolean("FixFisksuperheroesShaders", categoryShadersfixes, true, "Fixes rendering errors in Fisk's Superheroes mod when using shaders.");

        if(config.hasChanged()) {
            config.save();
        }
    }

}
