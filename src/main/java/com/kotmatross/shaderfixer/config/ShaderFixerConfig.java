package com.kotmatross.shaderfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {

    static final String CAT_VANILLA = "Minecraft";
    static final String CAT_ANGELICA = "Angelica";
    static final String CAT_NTM = "HbmsNuclearTechMod";
    static final String CAT_OC = "OpenComputers";
    static final String CAT_ELN = "ElectricalAge";
    static final String CAT_AVARITIA = "Avaritia";
    static final String CAT_DSURROUND = "DynamicSurroundings";
    static final String CAT_FISKHEROES = "FisksSuperheroes";
    static final String CAT_JOURNEYMAP = "JourneyMap";
    static final String CAT_TECHGUNS = "Techguns";
    static final String CAT_SIGNPIC = "SignPicture";
    static final String CAT_SCHEMATICA = "Schematica";

    public static boolean V_GUI_BLEND_FIX;
    public static boolean V_NORMALS_DISABLE_TAG;
    public static boolean V_MAIN_MENU_FPS_BYPASS;
    public static int V_MAIN_MENU_FPS_BYPASS_VALUE;
    public static boolean V_RIDING_HAND_ROTATION_FIX;
    public static boolean V_RIDING_HAND_ROTATION_DISABLE;
    public static boolean V_MODERN_RIDING_LEG_POS;
    public static boolean ANGELICA_TE_SHADOW_OFFSET;
    public static float ANGELICA_TE_SHADOW_OFFSET_FACTOR;
    public static boolean ANGELICA_TE_SHADOW_OFFSET_DEBUG_MODE;
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
    public static boolean SIGNPIC_FIX;
    public static boolean SCHEMATICA_FIX;

    public static void loadMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        V_GUI_BLEND_FIX = config.getBoolean(
            "VANILLA_GUI_BLEND_FIX",
            CAT_VANILLA,
            true,
            "Fixes an annoying bug due to which the effect bar in the creative menu turns black");

        V_NORMALS_DISABLE_TAG = config.getBoolean(
            "VANILLA_NORMALS_DISABLE_TAG",
            CAT_VANILLA,
            true,
            "Fixed incorrect rendering of many translucent things when rendering the entity's name tag");

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

        ANGELICA_TE_SHADOW_OFFSET = config.getBoolean(
            "ANGELICA_TE_SHADOW_OFFSET",
            CAT_ANGELICA,
            true,
            "[EXPERIMENTAL] Enables shadow offset for tile entities, which helps reduce self-shadowing (shadow acne) on huge tile entities. As a side effect, it may break shadows on small tile entities with large ANGELICA_TE_SHADOW_OFFSET_FACTOR values");

        ANGELICA_TE_SHADOW_OFFSET_FACTOR = config.getFloat(
            "ANGELICA_TE_SHADOW_OFFSET_FACTOR",
            CAT_ANGELICA,
            16.0F,
            0.0F,
            256.0F,
            "[EXPERIMENTAL] Shadow offset value. A higher value means less self-shadowing, but some TE elements may stop casting shadows at all");

        ANGELICA_TE_SHADOW_OFFSET_DEBUG_MODE = config.getBoolean(
            "ANGELICA_TE_SHADOW_OFFSET_DEBUG_MODE",
            CAT_ANGELICA,
            false,
            "[EXPERIMENTAL] Enables the ANGELICA_TE_SHADOW_OFFSET_FACTOR calibration mode. 'I' key to increase, 'K' key to decrease");

        NTM_MAIN_FIX = config.getBoolean("NTM_MAIN_FIX", CAT_NTM, true, "Main NTM patch. See README");

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
            "NTM_EXTENDED_HAZARD_DESCRIPTIONS",
            CAT_NTM,
            true,
            "Adds additional information to dangerous items (Ignites for / Explosion force / etc.)");

        OC_DISABLE_DL = config.getBoolean(
            "OC_DISABLE_DL",
            CAT_OC,
            true,
            "Forces OpenComputers to not use display lists for rendering. Significantly (?) reduces FPS, but can improve rendering with Angelica shaders");

        ELN_DISABLE_DL = config.getBoolean(
            "ELN_DISABLE_DL",
            CAT_ELN,
            true,
            "Forces Electrical Age to not use display lists for rendering. Significantly (?) reduces FPS, but can improve rendering with Angelica shaders");

        AVARITIA_FIX = config.getBoolean("AVARITIA_FIX", CAT_AVARITIA, true, "Main Avaritia patch. See README");

        DSURROUND_FIX = config
            .getBoolean("DSURROUND_FIX", CAT_DSURROUND, true, "Main DynamicSurroundings patch. See README");

        FISKHEROES_FIX = config
            .getBoolean("FISKHEROES_FIX", CAT_FISKHEROES, true, "Main FisksSuperheroes patch. See README");

        JOURNEYMAP_FIX = config.getBoolean("JOURNEYMAP_FIX", CAT_JOURNEYMAP, true, "Main JourneyMap patch. See README");

        TECHGUNS_FIX = config.getBoolean("TECHGUNS_FIX", CAT_TECHGUNS, true, "Main Techguns patch. See README");
        TECHGUNS_TEXTURE_FIX = config
            .getBoolean("TECHGUNS_TEXTURE_FIX", CAT_TECHGUNS, true, "Fixes transparency of some Techguns textures");

        SIGNPIC_FIX = config.getBoolean("SIGNPIC_FIX", CAT_SIGNPIC, true, "Main SignPicture patch. See README");

        SCHEMATICA_FIX = config.getBoolean("SCHEMATICA_FIX", CAT_SCHEMATICA, true, "Main Schematica patch. See README");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
