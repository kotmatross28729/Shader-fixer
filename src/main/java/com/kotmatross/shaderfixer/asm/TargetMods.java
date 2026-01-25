package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    ANGELICA("angelica", "com.gtnewhorizons.angelica.loading.AngelicaTweaker"),
    AVARITIA("Avaritia"),
    BACKHAND("backhand"),
    DSURROUND("dsurround"),
    ELN("Eln"),
    FISKHEROES("fiskheroes", "com.fiskmods.heroes.asm.SHLoadingPlugin"),
    NTM("hbm"),
    NTM_SPACE("com.hbm.dim.SolarSystem", true),
    JOURNEYMAP("journeymap"),
    NEI("NotEnoughItems"),
    OPENCOMPUTERS("OpenComputers"),
    SCHEMATICA("Schematica"),
    SIGNPIC("signpic"),
    TECHGUNS("Techguns"),;

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
