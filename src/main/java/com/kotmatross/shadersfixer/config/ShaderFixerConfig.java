package com.kotmatross.shadersfixer.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {
    // Definitely does NOT look like a BugTorch config

//LATE MIXINS
    //Base things
    public static boolean FixFisksuperheroesShaders;
    public static boolean FixNEIShaders;
    public static boolean FixTechgunsShaders;
    public static boolean FixDragonBlockCShaders;
    public static boolean FixZeldaSwordSkillsShaders;

    public static boolean TechgunsGoreLogger;
    public static List<String> TechgunsGoreList = new ArrayList<>();


    //Category
    static final String categoryShadersfixes = "Shaders fixes";
    static final String categorytweaks = "Tweaks & fixes";

    public static void loadMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixFisksuperheroesShaders = config.getBoolean("FixFisksuperheroesShaders", categoryShadersfixes, true, "Fixes rendering errors in Fisk's Superheroes mod when using shaders.");
        FixNEIShaders = config.getBoolean("FixNEIShaders", categoryShadersfixes, true, "Fixes rendering errors in NotEnoughItems mod when using shaders.");
        FixTechgunsShaders = config.getBoolean("FixTechgunsShaders", categoryShadersfixes, true, "Fixes errors and crashes in Techguns mod when using shaders.");
        FixDragonBlockCShaders = config.getBoolean("FixDragonBlockCShaders", categoryShadersfixes, true, "Fixes rendering errors in DragonBlockC mod when using shaders.");
        FixZeldaSwordSkillsShaders = config.getBoolean("FixZeldaSwordSkillsShaders", categoryShadersfixes, true, "Fixes rendering errors in Zelda Sword Skills mod when using shaders.");

        TechgunsGoreLogger = config.getBoolean("TechgunsGoreLogger", categorytweaks, false, "Prints the name of the killed mob( using techguns weapons) in the log, useful for TechgunsGoreList, where 1 parameter is the name of the mob.");
//        TechgunsGoreList = new ArrayList<>(Arrays.asList(config.getStringList("TechgunsGoreList", categorytweaks, new String[]{"entity_glyphid:6:0.66:85:156:17"}, "Adds an entity to the gore Techguns entity list. The syntax is: mobname:GibsNumber:GibsScale:BloodСolorR:BloodColorG:BloodColorBlue . Please note that Blood R-G-B colors can only be integers up to 255")));

        if(config.hasChanged()) {
            config.save();
        }
    }

//EARLY MIXINS

    public static boolean FixMinecraftHitboxesRender;
    public static boolean FixMinecraftFishinglineRender;
    public static boolean FixMinecraftLeashRender;
    public static boolean FixMinecraftEnderdragonDeathEffectsRender;
    public static boolean FixMinecraftLightningBoltRender;
    public static boolean FixMinecraftNameTagsRender;
    public static void loadEarlyMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixMinecraftHitboxesRender = config.getBoolean("FixMinecraftHitboxesRender", categoryShadersfixes, true, "Fixes hitbox rendering (F3 + B) with shaders.");
        FixMinecraftFishinglineRender = config.getBoolean("FixMinecraftFishinglineRender", categoryShadersfixes, true, "Fixes fishing line rendering (which is from a fishing rod) with shaders.");
        FixMinecraftLeashRender = config.getBoolean("FixMinecraftLeashRender", categoryShadersfixes, true, "Fixes leash line rendering with shaders.");
        FixMinecraftEnderdragonDeathEffectsRender = config.getBoolean("FixMinecraftEnderdragonDeathEffectsRender", categoryShadersfixes, true, "Fixes rendering of dragon death effects (purple flashes) with shaders.");
        FixMinecraftLightningBoltRender = config.getBoolean("FixMinecraftLightningBoltRender", categoryShadersfixes, true, "Fixes rendering of lightning bolt with shaders.");
        FixMinecraftNameTagsRender = config.getBoolean("FixMinecraftNameTagsRender", categoryShadersfixes, true, "Fixes rendering of name tags with shaders.");

        if(config.hasChanged()) {
            config.save();
        }
    }

}
