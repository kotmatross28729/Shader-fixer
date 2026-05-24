package com.kotmatross.shaderfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {

    // === CATEGORIES ===

    static final String CAT_VANILLA = "Minecraft";
    static final String CAT_ANGELICA = "Angelica";
    static final String CAT_NTM = "HbmsNuclearTechMod";
    static final String CAT_OC = "OpenComputers";
    static final String CAT_ELN = "ElectricalAge";
    static final String CAT_AVARITIA = "Avaritia";
    static final String CAT_DSURROUND = "DynamicSurroundings";
    static final String CAT_FISKHEROES = "FisksSuperheroes";
    static final String CAT_TECHGUNS = "Techguns";
    static final String CAT_SIGNPIC = "SignPicture";
    static final String CAT_SCHEMATICA = "Schematica";

    // === VALUES ===

    public static boolean V_GUI_BLEND_FIX;
    public static boolean V_XP_BAR_ALPHA_FIX;
    public static boolean V_PARTICLE_ALPHA_WORKAROUND;
    public static boolean V_NORMALS_DISABLE_TAG;
    public static int V_MAIN_MENU_FPS_BYPASS;
    public static int V_RIDING_HAND_ROTATION;
    public static boolean V_RIDING_LEG_POS;
    public static boolean NTM_MAIN_FIX;
    public static boolean NTM_SHADOW_FIX;
    public static boolean NTM_GUN_FIX;
    public static boolean NTM_ARMOR_FIX;
    public static boolean NTM_TEXTURE_FIX;
    public static boolean NTM_EXTENDED_HAZARD_DESCRIPTIONS;
    public static boolean OC_DISABLE_DL;
    public static boolean ELN_DISABLE_DL;
    public static boolean AVARITIA_FIX;
    public static boolean DSURROUND_FIX;
    public static boolean FISKHEROES_FIX;
    public static boolean TECHGUNS_FIX;
    public static boolean TECHGUNS_TEXTURE_FIX;
    public static boolean SIGNPIC_FIX;
    public static boolean SCHEMATICA_FIX;

    public static void loadMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        V_GUI_BLEND_FIX = config.getBoolean("VANILLA_GUI_BLEND_FIX"
            , CAT_VANILLA
            , true
            , "Fixes effect bar in creative menu turns black");

        V_XP_BAR_ALPHA_FIX = config.getBoolean("VANILLA_XP_BAR_ALPHA_FIX"
            , CAT_VANILLA
            , true
            , "Fixes experience bar black background if there is an item in the hotbar");

        V_PARTICLE_ALPHA_WORKAROUND = config.getBoolean("VANILLA_PARTICLE_ALPHA_WORKAROUND"
            , CAT_VANILLA
            , true
            , "Fixed translucent particles becoming dark with Complementary shaders enabled. (Uses GL_ONE instead of GL_SRC_ALPHA when rendering particles)");

        V_NORMALS_DISABLE_TAG = config.getBoolean("VANILLA_NORMALS_DISABLE_TAG"
            , CAT_VANILLA
            , true
            , "Fixed incorrect translucent renderer when rendering entity's name tag");

        V_MAIN_MENU_FPS_BYPASS = config.getInt("VANILLA_MAIN_MENU_FPS_BYPASS"
            , CAT_VANILLA
            , 144
            , -1
            , 8192
            , "By default, minecraft locks frame rate at 30 in the main menu. This option allows you to set any maximum FPS value in the main menu. 0 to use fps limit in settings, -1 to disable");

        V_RIDING_HAND_ROTATION = config.getInt("VANILLA_RIDING_HAND_ROTATION"
            , CAT_VANILLA
            , 2
            , 0
            , 2
            , "0 - Off. 1 - Updates 1st person hand rotation when player is riding/sitting, 2 - Disables 1st person hand rotation when player is riding/sitting (modern behavior)");

        V_RIDING_LEG_POS = config.getBoolean("VANILLA_RIDING_LEG_POS"
            , CAT_VANILLA
            , true
            , "Changes the position of the legs when riding, in accordance with modern versions (fixes legs passing through boat/minecart)");

        NTM_MAIN_FIX = config.getBoolean("NTM_MAIN_FIX"
            , CAT_NTM
            , true
            , "Main NTM patch. See README");
        
        NTM_SHADOW_FIX = config.getBoolean("NTM_SHADOW_FIX"
            , CAT_NTM
            , true
            , "Replaces the models of some machines with tessellated ones during shadow pass. Fixes shadow distortion and self-shadowing on them");
        
        NTM_GUN_FIX = config.getBoolean("NTM_GUN_FIX"
            , CAT_NTM
            , true
            , "Fixes new gun system (1.0.27_X5180+) rendering with shaders");

        NTM_ARMOR_FIX = config.getBoolean("NTM_ARMOR_FIX"
            , CAT_NTM
            , true
            , "Fixes rendering of akimbo weapons on mobs (+ mobs with NTM armor) + fixes rendering of akimbo weapons with CPM");

        NTM_TEXTURE_FIX = config.getBoolean("NTM_TEXTURE_FIX"
            , CAT_NTM
            , true
            , "Fixes transparency of some NTM textures");

        NTM_EXTENDED_HAZARD_DESCRIPTIONS = config.getBoolean("NTM_EXTENDED_HAZARD_DESCRIPTIONS"
            , CAT_NTM
            , true
            , "Adds additional information to dangerous items (Ignites for / Explosion force / etc.)");

        OC_DISABLE_DL = config.getBoolean("OC_DISABLE_DL"
            , CAT_OC
            , true
            , "[Workaround] Forces OpenComputers to not use display lists for rendering. Significantly (?) reduces FPS, but can improve rendering with Angelica shaders");

        ELN_DISABLE_DL = config.getBoolean("ELN_DISABLE_DL"
            , CAT_ELN
            , true
            , "[Workaround] Forces Electrical Age to not use display lists for rendering. Significantly (?) reduces FPS, but can improve rendering with Angelica shaders");

        AVARITIA_FIX = config.getBoolean("AVARITIA_FIX"
            , CAT_AVARITIA
            , true
            , "Main Avaritia patch. See README");

        DSURROUND_FIX = config.getBoolean("DSURROUND_FIX"
            , CAT_DSURROUND
            , true
            , "Main DynamicSurroundings patch. See README");

        FISKHEROES_FIX = config.getBoolean("FISKHEROES_FIX"
            , CAT_FISKHEROES
            , true
            , "Main FisksSuperheroes patch. See README");

        TECHGUNS_FIX = config.getBoolean("TECHGUNS_FIX"
            , CAT_TECHGUNS
            , true
            , "Main Techguns patch. See README");
        
        TECHGUNS_TEXTURE_FIX = config.getBoolean("TECHGUNS_TEXTURE_FIX"
            , CAT_TECHGUNS
            , true
            , "Fixes transparency of some Techguns textures");

        SIGNPIC_FIX = config.getBoolean("SIGNPIC_FIX"
            , CAT_SIGNPIC
            , true
            , "Main SignPicture patch. See README");

        SCHEMATICA_FIX = config.getBoolean("SCHEMATICA_FIX"
            , CAT_SCHEMATICA
            , true
            , "Main Schematica patch. See README");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
