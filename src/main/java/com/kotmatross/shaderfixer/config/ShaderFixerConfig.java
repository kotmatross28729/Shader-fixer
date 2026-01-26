package com.kotmatross.shaderfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {

    static final String CAT_VANILLA = "Minecraft";
    static final String CAT_NTM = "Hbm's Nuclear Tech Mod";
    static final String CAT_OC = "OpenComputers";
    static final String CAT_ELN = "Electrical Age";
    static final String CAT_AVARITIA = "Avaritia";
    static final String CAT_DSURROUND = "Dynamic Surroundings";
    static final String CAT_FISKHEROES = "Fisk's Superheroes";
    static final String CAT_JOURNEYMAP = "JourneyMap";
    static final String CAT_TECHGUNS = "Techguns";

    public static boolean V_GUI_BLEND_FIX;
    public static boolean V_MAIN_MENU_FPS_BYPASS;
    public static int V_MAIN_MENU_FPS_BYPASS_VALUE;
    public static boolean V_RIDING_HAND_ROTATION_FIX;
    public static boolean V_RIDING_HAND_ROTATION_DISABLE;
    public static boolean V_MODERN_RIDING_LEG_POS;
    public static boolean NTM_MAIN_FIX;
    public static boolean NTM_GUN_FIX;
    public static boolean NTM_ARMOR_FIX;
    public static boolean NTM_TEXTURE_FIX;
    public static boolean NTM_EXTENDED_HAZARD_DESCRIPTIONS;
    public static boolean OC_DISABLE_DL;
    public static boolean ELN_DISABLE_DL;
    public static boolean AVARITIA_FIX;
    public static boolean DSURROUND_FIX;
    public static boolean FISKHEROES_FIX;
    public static boolean JOURNEYMAP_FIX;
    public static boolean TECHGUNS_FIX;
    public static boolean TECHGUNS_TEXTURE_FIX;

    public static void loadMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        V_GUI_BLEND_FIX = config.getBoolean(
            "VANILLA_GUI_BLEND_FIX",
            CAT_VANILLA,
            true,
            "Fixes an annoying bug due to which the effect bar in the creative menu turns black");

        V_MAIN_MENU_FPS_BYPASS = config.getBoolean(
            "VANILLA_MAIN_MENU_FPS_BYPASS",
            CAT_VANILLA,
            true,
            "By default, minecraft locks frame rate at 30 in the main menu. In modern versions this value is 60, which is 2 times smoother than in 1.7.10. This option allows you to set any maximum FPS value in the main menu");

        V_MAIN_MENU_FPS_BYPASS_VALUE = config.getInt(
            "VANILLA_MAIN_MENU_FPS_BYPASS_VALUE",
            CAT_VANILLA,
            144,
            0,
            8192,
            "Maximum FPS in the main menu (see VANILLA_MAIN_MENU_FPS_BYPASS). 0 to use fps limit in settings");

        V_RIDING_HAND_ROTATION_FIX = config.getBoolean(
            "VANILLA_RIDING_HAND_ROTATION_FIX",
            CAT_VANILLA,
            true,
            "Fixes bug due to which the hand wouldn't update rotation when the player was riding/sitting");

        V_RIDING_HAND_ROTATION_DISABLE = config.getBoolean(
            "VANILLA_RIDING_HAND_ROTATION_DISABLE",
            CAT_VANILLA,
            false,
            "Disables 1st person hand rotation when the player is riding/sitting (similar to modern versions). Requires VANILLA_RIDING_HAND_ROTATION_FIX to be false");

        V_MODERN_RIDING_LEG_POS = config.getBoolean(
            "VANILLA_MODERN_RIDING_LEG_POS",
            CAT_VANILLA,
            true,
            "Changes the position of the legs when riding, in accordance with modern versions (fixes legs passing through boat/minecart)");

        NTM_MAIN_FIX = config.getBoolean("NTM_MAIN_FIX", CAT_NTM, true, "ADD LATER");

        NTM_GUN_FIX = config
            .getBoolean("NTM_GUN_FIX", CAT_NTM, true, "Fixes new gun system (1.0.27_X5180+) rendering with shaders");

        NTM_ARMOR_FIX = config.getBoolean(
            "NTM_ARMOR_FIX",
            CAT_NTM,
            true,
            "Fixes rendering of akimbo weapons on mobs (+ mobs with NTM armor) + fixes rendering of akimbo weapons with CPM");

        NTM_TEXTURE_FIX = config
            .getBoolean("NTM_TEXTURE_FIX", CAT_NTM, true, "Fixes transparency of some NTM textures");

        NTM_EXTENDED_HAZARD_DESCRIPTIONS = config.getBoolean(
            "HBM_EXTENDED_HAZARD_DESCRIPTIONS",
            CAT_NTM,
            true,
            "Adds additional information to dangerous items (Ignites for / Explosion force / etc.)");

        OC_DISABLE_DL = config.getBoolean(
            "OC_DISABLE_DL",
            CAT_OC,
            false,
            "Forces OpenComputers to not use display lists for rendering. Significantly reduces FPS, but can improve rendering with Angelica shaders");

        ELN_DISABLE_DL = config.getBoolean(
            "ELN_DISABLE_DL",
            CAT_ELN,
            false,
            "Forces Electrical Age to not use display lists for rendering. Significantly reduces FPS, but can improve rendering with Angelica shaders");

        AVARITIA_FIX = config.getBoolean("AVARITIA_FIX", CAT_AVARITIA, true, "ADD LATER");

        DSURROUND_FIX = config.getBoolean("DSURROUND_FIX", CAT_DSURROUND, true, "ADD LATER");

        FISKHEROES_FIX = config.getBoolean("FISKHEROES_FIX", CAT_FISKHEROES, true, "ADD LATER");

        JOURNEYMAP_FIX = config.getBoolean("JOURNEYMAP_FIX", CAT_JOURNEYMAP, true, "ADD LATER");

        TECHGUNS_FIX = config.getBoolean("TECHGUNS_FIX", CAT_TECHGUNS, true, "ADD LATER");
        TECHGUNS_TEXTURE_FIX = config.getBoolean("TECHGUNS_TEXTURE_FIX", CAT_TECHGUNS, true, "ADD LATER");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
