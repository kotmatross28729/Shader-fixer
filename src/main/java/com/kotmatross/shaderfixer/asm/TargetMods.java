package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    ANGELICA("angelica", "com.gtnewhorizons.angelica.loading.AngelicaTweaker"),
    AVARITIA("Avaritia"),
    BACKHAND("backhand"),
    CUSTOMNPCS("customnpcs"),
    CUSTOMPLAYERMODELS("customplayermodels"),
    DBCR("jinryuubetterrenderaddon"),
    DBC("jinryuudragonblockc"),
    DSURROUND("dsurround"),
    ELN("Eln"),
    FINDIT("findit"),
    FISKHEROES("fiskheroes", "com.fiskmods.heroes.asm.SHLoadingPlugin"),
    HBM("hbm"),
    HBM_SPACE("com.hbm.dim.SolarSystem", true),
    HEE("HardcoreEnderExpansion"),
    JOURNEYMAP("journeymap"),
    LMMX("lmmx"),
    MANEUVERGEAR("3DManeuverGear"),
    MAPLETREE("mod_ecru_MapleTree"),
    MCHELI("mcheli"),
    NEI("NotEnoughItems"),
    NEI_OG("NotEnoughItems"),
    OPENCOMPUTERS("OpenComputers"),
    RIVALREBELS("rivalrebels"),
    SCHEMATICA("Schematica"),
    SIGNPIC("signpic"),
    TECHGUNS("Techguns"),
    WEAPONMOD("weaponmod"),
    ZELDASWORDSKILLS("zeldaswordskills");

    private final TargetModBuilder builder;

    TargetMods(String modId) {
        this(modId, null, null);
    }

    TargetMods(String modId, String coreModClass) {
        this(modId, null, coreModClass);
    }

    TargetMods(String targetClass, boolean ignoredOnlyClass) {
        this(null, targetClass, null);
    }

    TargetMods(String modId, String targetClass, String coreModClass) {
        this.builder = new TargetModBuilder().setModId(modId)
            .setTargetClass(targetClass)
            .setCoreModClass(coreModClass);
    }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }
}
