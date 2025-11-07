package com.kotmatross.shaderfixer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ShaderFixerConfig {

    static final String CAT_SHADER = "SHADER FIXES";
    static final String CAT_TWEAKS = "TWEAKS & FIXES";

    // EARLY MIXINS

    // GENERAL
    public static boolean VANILLA_FIX;
    public static boolean NTM_GUNFIX;

    // TWEAKS
    public static boolean NTM_ARMORFIX;
    public static boolean OPTIFINE_CRASH;
    public static boolean VANILLA_DISABLE_HORIZON;
    public static boolean VANILLA_GUI_BLEND_FIX;
    public static boolean VANILLA_MAIN_MENU_FPS_BYPASS;
    public static int VANILLA_MAIN_MENU_FPS_BYPASS_VALUE;
    public static boolean VANILLA_RIDING_HAND_ROTATION_FIX;
    public static boolean VANILLA_RIDING_HAND_ROTATION_DISABLE;
    public static boolean VANILLA_MODERN_RIDING_LEG_POS;

    public static void loadEarlyMixinConfigGeneral(File configFile) {
        Configuration config = new Configuration(configFile);

        VANILLA_FIX = config.getBoolean(
            "FIX_VANILLA",
            CAT_SHADER,
            true,
            "Fixes: | hitbox rendering / fishing line / leash line / dragon death effects / lightning bolt / name tags | with shaders");

        NTM_GUNFIX = config.getBoolean(
            "NTM_GUNFIX",
            CAT_SHADER,
            true,
            "[HBM'S NTM] Fixes new gun system (1.0.27_X5180+) with shaders");

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void loadEarlyMixinConfigTweaks(File configFile) {
        Configuration config = new Configuration(configFile);

        NTM_ARMORFIX = config.getBoolean(
            "NTM_ARMORFIX",
            CAT_TWEAKS,
            true,
            "[HBM'S NTM] Fixes rendering of akimbo weapons on mobs (+ mobs with NTM armor) + fixes rendering of akimbo weapons with CPM");

        OPTIFINE_CRASH = config.getBoolean(
            "OPTIFINE_CRASH",
            CAT_TWEAKS,
            true,
            "Crashes the game if it detects optifine. Only disable it if you know what you are doing...");

        VANILLA_DISABLE_HORIZON = config.getBoolean(
            "VANILLA_DISABLE_HORIZON",
            CAT_TWEAKS,
            true,
            "Removes the incredibly ugly horizon (the thing that cuts off the bottom of the skybox), I have no idea why they added it, it's horrible (Also works with NTM:Space)");

        VANILLA_GUI_BLEND_FIX = config.getBoolean(
            "VANILLA_GUI_BLEND_FIX",
            CAT_TWEAKS,
            true,
            "Fixes an annoying bug due to which the effect bar in the creative menu turns black");

        VANILLA_MAIN_MENU_FPS_BYPASS = config.getBoolean(
            "VANILLA_MAIN_MENU_FPS_BYPASS",
            CAT_TWEAKS,
            true,
            "By default, minecraft locks your frame rate to 30 in the main menu. In new versions of the game this value is 60, which is 2 times smoother than in 1.7.10. This option allows you to set any maximum FPS value in the main menu");

        VANILLA_MAIN_MENU_FPS_BYPASS_VALUE = config.getInt(
            "VANILLA_MAIN_MENU_FPS_BYPASS_VALUE",
            CAT_TWEAKS,
            144,
            -1,
            8192,
            "Maximum number of frames in the main menu (see VANILLA_MAIN_MENU_FPS_BYPASS). -1 or 0 to use fps limit in settings");

        VANILLA_RIDING_HAND_ROTATION_FIX = config.getBoolean(
            "VANILLA_RIDING_HAND_ROTATION_FIX",
            CAT_TWEAKS,
            true,
            "Fixes bug due to which the hand wouldn't update rotation when the player was riding/sitting");

        VANILLA_RIDING_HAND_ROTATION_DISABLE = config.getBoolean(
            "VANILLA_RIDING_HAND_ROTATION_DISABLE",
            CAT_TWEAKS,
            false,
            "Disables 1st person hand rotation when the player is riding/sitting (similar to newer versions). Requires VANILLA_RIDING_HAND_ROTATION_FIX to be false");

        VANILLA_MODERN_RIDING_LEG_POS = config.getBoolean(
            "VANILLA_MODERN_RIDING_LEG_POS",
            CAT_TWEAKS,
            true,
            "Changes the position of the legs when riding, in accordance with new versions (fixes legs passing through boat/minecart)");

        if (config.hasChanged()) {
            config.save();
        }
    }

    // LATE MIXINS

    public static boolean NTM_TEXTURE_FIX;
    public static boolean CPM_EXP_FIX;
    public static boolean OC_DISABLE_DL;
    public static boolean ELN_DISABLE_DL;

    public static void loadLateMixinConfigGeneral(File configFile) {
        Configuration config = new Configuration(configFile);

        NTM_TEXTURE_FIX = config.getBoolean(
            "NTM_TEXTURE_FIX",
            CAT_SHADER,
            true,
            "[HBM'S NTM] Fixes transparency of some NTM textures. I'm not an artist, so it might not look very good without using shaders");

        CPM_EXP_FIX = config.getBoolean(
            "CPM_EXP_FIX",
            CAT_SHADER,
            true,
            "[Customizable Player Models] Experimental! Fixes models whose cubes don't have texture (Mode: Color)");

        OC_DISABLE_DL = config.getBoolean(
            "OC_DISABLE_DL",
            CAT_SHADER,
            true,
            "[OpenComputers] Forces OС to not use display lists for rendering. Significantly reduces FPS, but improves rendering with Angelica shaders");

        ELN_DISABLE_DL = config.getBoolean(
            "ELN_DISABLE_DL",
            CAT_SHADER,
            true,
            "[Electrical Age] Forces Electrical Age to not use display lists for rendering. Significantly reduces FPS, but improves rendering with Angelica shaders");

        if (config.hasChanged()) {
            config.save();
        }

    }

    public static boolean NTM_SPACE_DISABLE_PLANET_RENDER;
    public static boolean ELN_LIGHT_MIXINS;
    public static boolean FISKHEROES_CPM_COMPAT;
    public static boolean HBM_EXTENDED_HAZARD_DESCRIPTIONS;
    public static boolean HBM_MUZZLE_FLASH_ENABLE_DEPTH;

    public static void loadLateMixinConfigTweaks(File configFile) {
        Configuration config = new Configuration(configFile);

        NTM_SPACE_DISABLE_PLANET_RENDER = config.getBoolean(
            "NTM_SPACE_DISABLE_PLANET_RENDER",
            CAT_TWEAKS,
            false,
            "[HBM'S NTM:SPACE] Disables rendering of the planet under the player, at high altitude");

        FISKHEROES_CPM_COMPAT = config.getBoolean(
            "FISKHEROES_CPM_COMPAT",
            CAT_TWEAKS,
            false,
            "[UNSTABLE] [Fisk's Superheroes] Fixes a bug where CPM animations wouldn't work with Fisksuperheroes armor");

        ELN_LIGHT_MIXINS = config.getBoolean(
            "ELN_LIGHT_MIXINS",
            CAT_TWEAKS,
            true,
            "[Electrical Age] Slightly modifies the code for rendering the light sprite to avoid an issue with bsl shaders that renders object with full alpha when the object's alpha is close to 0 (below 0.1). Use carefully with new versions, as it calls the old sprite rendering code");

        HBM_EXTENDED_HAZARD_DESCRIPTIONS = config.getBoolean(
            "HBM_EXTENDED_HAZARD_DESCRIPTIONS",
            CAT_TWEAKS,
            true,
            "[HBM's NTM] Adds additional information to dangerous items (Ignites for / Explosion force / etc.)");

        HBM_MUZZLE_FLASH_ENABLE_DEPTH = config.getBoolean(
            "HBM_MUZZLE_FLASH_ENABLE_DEPTH",
            CAT_TWEAKS,
            false,
            "[HBM's NTM] (Causes z-fighting!) Turns glDepthMask back on when rendering muzzle flash. Fixes all sorts of errors with shader effects (clouds/water, etc.). Better to use with NTM_TEXTURE_FIX enabled");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
