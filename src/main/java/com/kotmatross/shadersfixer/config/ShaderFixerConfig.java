package com.kotmatross.shadersfixer.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean FixAvaritiaShaders;

    public static boolean FixThaumicConciliumShaders;

    public static boolean FixOpenComputersShaders;
    public static boolean FixElnShaders;

    public static boolean FixHbmShaders;

    public static boolean FixDSShaders;

    public static boolean FixLMMEShaders;

    public static boolean FixHEEhaders;
    public static boolean PatchHBMAngelica;
    
    public static boolean FixCNPCShaders;
    
    public static boolean FixSignPictureShaders;

    public static boolean TechgunsGoreLogger;
    public static List<String> TechgunsGoreList = new ArrayList<>();

    public static boolean ElnLightMixins;
    public static boolean HbmExtendedHazardDescriptions;

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

        FixAvaritiaShaders = config.getBoolean("FixAvaritiaShaders", categoryShadersfixes, true, "Fixes rendering errors in Avaritia mod when using shaders.");
        FixThaumicConciliumShaders = config.getBoolean("FixThaumicConciliumShaders", categoryShadersfixes, true, "Fixes rendering errors in Thaumic Concilium mod when using shaders.");

        FixOpenComputersShaders = config.getBoolean("FixOpenComputersShaders", categoryShadersfixes, true, "Fixes rendering errors in OpenComputers mod when using shaders.");
        FixElnShaders = config.getBoolean("FixElnShaders", categoryShadersfixes, true, "Fixes rendering errors in ElectricalAge mod when using shaders.");
        FixHbmShaders = config.getBoolean("FixHbmShaders", categoryShadersfixes, true, "Fixes rendering errors in Hbm's NTM mod when using shaders.");
        FixDSShaders = config.getBoolean("FixDSShaders", categoryShadersfixes, true, "Fixes rendering errors in DynamicSurroundings mod when using shaders.");
        FixLMMEShaders = config.getBoolean("FixLMMEhaders", categoryShadersfixes, true, "Disables mob rendering in LittleMaidMobEnhanced interaction GUI to fix shaders.");
        FixHEEhaders = config.getBoolean("FixHEEhaders", categoryShadersfixes, true, "Fixes rendering errors in HardcoreEnderExpansion mod when using shaders.");
        PatchHBMAngelica = config.getBoolean("PatchHBMAngelica", categoryShadersfixes, true, "Patches the HandRenderer class to work correctly with Hbm's NTM gun fix.");
        FixCNPCShaders = config.getBoolean("FixCNPCShaders", categoryShadersfixes, true, "Fixes rendering errors in CustomNPC mod when using shaders.");
        FixSignPictureShaders = config.getBoolean("FixSignPictureShaders", categoryShadersfixes, true, "Fixes rendering errors in SignPicture mod when using shaders.");
        
        ElnLightMixins = config.getBoolean("ElnLightMixins", categorytweaks, true, "Slightly modifies the code for rendering the light sprite to avoid an issue with bsl shaders that renders object with full alpha when the object's alpha is close to 0 (below 0.1). Use carefully with new versions, as it calls the old sprite rendering code");

        HbmExtendedHazardDescriptions = config.getBoolean("HbmExtendedHazardDescriptions", categorytweaks, true, "Adds additional information to dangerous items [HBM's NTM]");

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

    public static boolean EnableXMixinRenderLiving;
    public static boolean FixMinecraftEnderdragonDeathEffectsRender;
    public static boolean FixMinecraftLightningBoltRender;
    public static boolean FixMinecraftNameTagsRender;

    public static boolean FixMinecraftEffectGUIBlending;
    
    public static boolean enableNotifications;
    public static int startTicksOffset;
    public static int ticksInterval;

    public static boolean FixHbmGunsRender;

    public static boolean UnlockMainMenuFPS;
    public static int MainMenuFPSValue;
    
    public static boolean FixRiddingHand;
    
    public static boolean DisableRiddingHandRotation;
    
    public static boolean optifineNTMSpaceCrash;
    
    public static void loadEarlyMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        FixHbmGunsRender = config.getBoolean("FixHbmGunsRender", categoryShadersfixes, true, "Uses a fairly complex mixin system to fix new guns with shaders.");

        FixMinecraftHitboxesRender = config.getBoolean("FixMinecraftHitboxesRender", categoryShadersfixes, true, "Fixes hitbox rendering (F3 + B) with shaders.");
        FixMinecraftFishinglineRender = config.getBoolean("FixMinecraftFishinglineRender", categoryShadersfixes, true, "Fixes fishing line rendering (which is from a fishing rod) with shaders.");
        FixMinecraftLeashRender = config.getBoolean("FixMinecraftLeashRender", categoryShadersfixes, true, "Fixes leash line rendering with shaders.");
        EnableXMixinRenderLiving = config.getBoolean("EnableXMixinRenderLiving", categoryShadersfixes, true, "Enables XMixinRenderLiving which should fix DamageIndicators GUI rendering with shaders.");
        FixMinecraftEnderdragonDeathEffectsRender = config.getBoolean("FixMinecraftEnderdragonDeathEffectsRender", categoryShadersfixes, true, "Fixes rendering of dragon death effects (purple flashes) with shaders.");
        FixMinecraftLightningBoltRender = config.getBoolean("FixMinecraftLightningBoltRender", categoryShadersfixes, true, "Fixes rendering of lightning bolt with shaders.");
        FixMinecraftNameTagsRender = config.getBoolean("FixMinecraftNameTagsRender", categoryShadersfixes, true, "Fixes rendering of name tags with shaders.");
        FixMinecraftEffectGUIBlending = config.getBoolean("FixMinecraftEffectGUIBlending", categoryShadersfixes, true, "Fixes an annoying bug due to which the effect bar in the creative menu turns black.");
      
        enableNotifications = config.getBoolean("enableNotifications", categorytweaks, true, "Turns on a notification in the chat when detected 'old version' mods.");
        startTicksOffset = config.getInt("startTicksOffset", categorytweaks, 50,0, 1024, "First update notification will be delayed by n ticks to be displayed last at chat.");
        ticksInterval = config.getInt("ticksInterval", categorytweaks, 10,0, 1024, "Update notifications will be delayed by n ticks after the last notification (to avoid a sudden influx of notifications).");

        UnlockMainMenuFPS = config.getBoolean("UnlockMainMenuFPS", categorytweaks, true, "By default, minecraft locks your frame rate to 30 in the main menu. In new versions of the game this value is 60, which is 2 times smoother than in 1.7.10. This option allows you to set any maximum FPS value in the main menu.");
        MainMenuFPSValue = config.getInt("MainMenuFPSValue", categorytweaks, 144,-1, 1024, "Maximum number of frames in the main menu (see UnlockMainMenuFPS). -1 or 0 to use fps limit in settings.");
    
        FixRiddingHand = config.getBoolean("FixRiddingHand", categorytweaks, true, "Fixes bug due to which the hand wouldn't update rotation when the player was riding/sitting.");
        DisableRiddingHandRotation = config.getBoolean("DisableRiddingHandRotation", categorytweaks, false, "Disables 1st person hand rotation when the player is riding/sitting (similar to newer versions). Requires FixRiddingHand to be false.");
    
        optifineNTMSpaceCrash = config.getBoolean("optifineNTMSpaceCrash", categorytweaks, true, "Crashes the game if it detects optifine with Hbm's NTM:Space. Only disable it if you know what you are doing...");
    
        if(config.hasChanged()) {
            config.save();
        }
    }
    
}
