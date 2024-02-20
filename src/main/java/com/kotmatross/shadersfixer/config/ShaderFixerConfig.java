package com.kotmatross.shadersfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {
    // Definitely does NOT look like a BugTorch config

//LATE MIXINS
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

//EARLY MIXINS

    public static boolean FixMinecraftHitboxesRender;
    public static boolean FixMinecraftFishinglineRender;
    public static void loadEarlyMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixMinecraftHitboxesRender = config.getBoolean("FixMinecraftHitboxesRender", categoryShadersfixes, true, "Fixes hitbox rendering (F3 + B) with shaders.");
        FixMinecraftFishinglineRender = config.getBoolean("FixMinecraftFishinglineRender", categoryShadersfixes, true, "Fixes fishing line rendering (which is from a fishing rod) with shaders.");


        if(config.hasChanged()) {
            config.save();
        }
    }

}
