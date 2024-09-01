package com.kotmatross.shadersfixer.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.kotmatross.shadersfixer.Utils;
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
    public static boolean FixMcheliOShaders;

    public static boolean FixRivalRebelsShaders;
    public static boolean FixSchematicaShaders;
    public static boolean FixJourneymapShaders;

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
        FixMcheliOShaders = config.getBoolean("FixMcheliOShaders", categoryShadersfixes, true, "Fixes rendering errors in Mcheli Overdrive mod when using shaders.");
        FixRivalRebelsShaders = config.getBoolean("FixRivalRebelsShaders", categoryShadersfixes, true, "Fixes rendering errors in Rival Rebels mod when using shaders.");
        FixSchematicaShaders = config.getBoolean("FixSchematicaShaders", categoryShadersfixes, true, "Fixes rendering errors in Schematica mod when using shaders.");
        FixJourneymapShaders = config.getBoolean("FixJourneymapShaders", categoryShadersfixes, true, "Fixes rendering errors in Journeymap mod when using shaders.");

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

    public static boolean enableNotifications;
    public static void loadEarlyMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixMinecraftHitboxesRender = config.getBoolean("FixMinecraftHitboxesRender", categoryShadersfixes, true, "Fixes hitbox rendering (F3 + B) with shaders.");
        FixMinecraftFishinglineRender = config.getBoolean("FixMinecraftFishinglineRender", categoryShadersfixes, true, "Fixes fishing line rendering (which is from a fishing rod) with shaders.");
        FixMinecraftLeashRender = config.getBoolean("FixMinecraftLeashRender", categoryShadersfixes, true, "Fixes leash line rendering with shaders.");
        FixMinecraftEnderdragonDeathEffectsRender = config.getBoolean("FixMinecraftEnderdragonDeathEffectsRender", categoryShadersfixes, true, "Fixes rendering of dragon death effects (purple flashes) with shaders.");
        FixMinecraftLightningBoltRender = config.getBoolean("FixMinecraftLightningBoltRender", categoryShadersfixes, true, "Fixes rendering of lightning bolt with shaders.");
        FixMinecraftNameTagsRender = config.getBoolean("FixMinecraftNameTagsRender", categoryShadersfixes, true, "Fixes rendering of name tags with shaders.");
        enableNotifications = config.getBoolean("enableNotifications", categorytweaks, true, "Turns on a notification in the chat when detected mods for which I (kotmatross) have useful forks.");

        if(config.hasChanged()) {
            config.save();
        }
    }


//Lighting Fix
    static final String categoryLightingFix = "Lighting Fix";
    public static boolean LightingFix = false;
    public static boolean LightingFixCreeper = false;

    public static int LightingFixRange = 2147483647;

    public static boolean ForceDisableLightingFix = true;

    public static void loadLightingFixConfig(File configFile) {
        Configuration config = new Configuration(configFile);
        LightingFix = config.getBoolean("LightingFix", categoryLightingFix, false, "Enables LightingFix - a special mob that, due to its rendering features, fixes the “Infamous lighting bug” (more details on the mod wiki)");
        LightingFixCreeper = config.getBoolean("LightingFixCreeper", categoryLightingFix, false, "Function that displays LightingFix mob as a creeper");
        LightingFixRange = config.getInt("LightingFixRange", categoryLightingFix, Utils.INT_MAX,1, Utils.INT_MAX, "\"trackingRange\" of the LightingFix mob, I recommend not touching it");
        ForceDisableLightingFix = config.getBoolean("ForceDisableLightingFix", categoryLightingFix, true, "By default, LightingFix is automatically enabled when a mod, that changes the shader component, is detected. This option allows you to intentionally turn off LightingFix even if such mods are detected");

        if(config.hasChanged()) {
            config.save();
        }
    }

}
