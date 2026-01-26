package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@SuppressWarnings("unused")
public enum LateMixins implements IMixins {

    ANGELICA_NTM_GUNFIX_COMPAT(new MixinBuilder("Patches HandRenderer to work correctly with HBM's NTM gunfix")
        .addRequiredMod(TargetMods.ANGELICA)
        .addRequiredMod(TargetMods.NTM)
        .addClientMixins("angelica.MixinHandRenderer")
        .setApplyIf(() -> ShaderFixerConfig.NTM_GUN_FIX)),

    BACKHAND_NTM_GUNFIX_COMPAT(new MixinBuilder(
        "Cancel rendering of the offhand item if item in the main hand is NTM gun (otherwise it causes rendering bugs)")
            .addRequiredMod(TargetMods.BACKHAND)
            .addRequiredMod(TargetMods.NTM)
            .addClientMixins("backhand.MixinBackhandRenderHelper", "backhand.MixinItemRendererHooks")
            .setApplyIf(() -> ShaderFixerConfig.NTM_GUN_FIX)),

    AVARITIA(new MixinBuilder().addRequiredMod(TargetMods.AVARITIA)
        .addClientMixins(
            "avaritia.MixinCosmicRenderShenanigans",
            "avaritia.MixinModelArmorInfinity",
            "avaritia.MixinRenderHeavenArrow")
        .setApplyIf(() -> ShaderFixerConfig.AVARITIA_FIX)),

    DSURROUND(new MixinBuilder().addRequiredMod(TargetMods.DSURROUND)
        .addClientMixins("dsurround.MixinAuroraRenderer")
        .setApplyIf(() -> ShaderFixerConfig.DSURROUND_FIX)),

    ELN_DL_DISABLE(new MixinBuilder().addRequiredMod(TargetMods.ELN)
        .addRequiredMod(TargetMods.ANGELICA)
        .addClientMixins("eln.MixinSixNodeElementRender_disableDisplayList")
        .setApplyIf(() -> ShaderFixerConfig.ELN_DISABLE_DL)),

    FISKHEROES(new MixinBuilder().addRequiredMod(TargetMods.FISKHEROES)
        .addClientMixins(
            "fiskheroes.MixinBeamRendererLaser",
            "fiskheroes.MixinBeamRendererLightning",
            "fiskheroes.MixinBeamRendererLine",
            "fiskheroes.MixinEffectTentacles",
            "fiskheroes.MixinEntitySHSpellWaveFX",
            "fiskheroes.MixinRenderEnergyBolt",
            "fiskheroes.MixinRenderSpellWhip",
            "fiskheroes.MixinRenderSuitFabricator",
            "fiskheroes.MixinRenderSuitDatabase",
            "fiskheroes.MixinShapeFormatCircles",
            "fiskheroes.MixinShapeFormatLines",
            "fiskheroes.MixinShapeFormatWireframe")
        .setApplyIf(() -> ShaderFixerConfig.FISKHEROES_FIX)),

    NTM(new MixinBuilder().addRequiredMod(TargetMods.NTM)
        .addClientMixins(
            "ntm.MixinArmorEnvsuit",
            "ntm.MixinDiamondPronter",
            "ntm.MixinItemRendererMeteorSword",
            "ntm.MixinParticleAmatFlash",
            "ntm.MixinRenderBeam",
            "ntm.MixinRenderBlackHole",
            "ntm.MixinRenderChemical",
            "ntm.MixinRenderCore",
            "ntm.MixinRenderDeathBlast",
            "ntm.MixinRenderDemonLamp",
            "ntm.MixinRenderMachineForceField",
            "ntm.MixinRenderOverhead",
            "ntm.MixinRenderPylonBase",
            "ntm.MixinRenderRBMKLid",
            "ntm.MixinRenderRefueler",
            "ntm.MixinRenderSiegeLaser",
            "ntm.MixinRenderSmallReactor",
            "ntm.MixinRenderSolarBoiler",
            "ntm.MixinRenderSparks",
            "ntm.MixinRenderSpear",
            "ntm.MixinRenderTorex")
        .setApplyIf(() -> ShaderFixerConfig.NTM_MAIN_FIX)),

    NTM_GUNFIX(new MixinBuilder().addRequiredMod(TargetMods.NTM)
        .addClientMixins(
            "ntm.sedna.MixinHbmAnimations",
            "ntm.sedna.MixinItemRenderWeaponBase",
            "ntm.sedna.MixinLegoClient",
            "ntm.sedna.MixinModEventHandlerRenderer")
        .setApplyIf(() -> ShaderFixerConfig.NTM_GUN_FIX)),

    NTM_OG_ONLY(new MixinBuilder().addRequiredMod(TargetMods.NTM)
        .addExcludedMod(TargetMods.NTM_SPACE)
        .addClientMixins("ntm.MixinBeamPronter")
        .setApplyIf(() -> ShaderFixerConfig.NTM_MAIN_FIX)),

    NTM_SPACE(new MixinBuilder().addRequiredMod(TargetMods.NTM_SPACE)
        .addClientMixins(
            "ntm.space.MixinBeamPronter_SPACE",
            "ntm.space.MixinSkyProviderCelestial",
            "ntm.space.MixinMissilePronter")
        .setApplyIf(() -> ShaderFixerConfig.NTM_MAIN_FIX)),

    JOURNEYMAP(new MixinBuilder().addRequiredMod(TargetMods.JOURNEYMAP)
        .addClientMixins("journeymap.MixinDrawUtil")
        .setApplyIf(() -> ShaderFixerConfig.JOURNEYMAP_FIX)),

    /// TODO: test with 2.x

    // OPENCOMPUTERS(new MixinBuilder().addRequiredMod(TargetMods.OPENCOMPUTERS)
    // .addClientMixins("opencomputers.MixinScreenRenderer")),

    OPENCOMPUTERS_DL_DISABLE(new MixinBuilder().addRequiredMod(TargetMods.OPENCOMPUTERS)
        .addRequiredMod(TargetMods.ANGELICA)
        .addClientMixins("opencomputers.MixinRenderState_disableDisplayList")
        .setApplyIf(() -> ShaderFixerConfig.OC_DISABLE_DL)),

    /// TODO: TBA

    // SCHEMATICA(new MixinBuilder().addRequiredMod(TargetMods.SCHEMATICA)
    // .addClientMixins(
    // "SCHEMATICA.MixinRendererSchematicChunk",
    // "SCHEMATICA.MixinRendererSchematicGlobal",
    // "SCHEMATICA.MixinRenderHelper")),

    // SIGNPIC(new MixinBuilder().addRequiredMod(TargetMods.SIGNPIC)
    // .addClientMixins(
    // "SIGNPIC.MixinCustomTileEntitySignRenderer",
    // "SIGNPIC.MixinRenderHelper",
    // "SIGNPIC.MixinStateRender")),

    TECHGUNS(new MixinBuilder().addRequiredMod(TargetMods.TECHGUNS)
        .addClientMixins("techguns.MixinRenderTGChest", "techguns.MixinTGRenderHelper", "techguns.MixinRenderTeslaBeam")
        .setApplyIf(() -> ShaderFixerConfig.TECHGUNS_FIX)),

    // QOL / Other stuff

    HBM_EXTENDED_HAZARD(new MixinBuilder().addRequiredMod(TargetMods.NTM)
        .addClientMixins(
            "ntm.descr.MixinHazardTypeAsbestos",
            "ntm.descr.MixinHazardTypeBlinding",
            "ntm.descr.MixinHazardTypeCoal",
            "ntm.descr.MixinHazardTypeExplosive",
            "ntm.descr.MixinHazardTypeHot",
            "ntm.descr.MixinHazardTypeHydroactive")
        .setApplyIf(() -> ShaderFixerConfig.NTM_EXTENDED_HAZARD_DESCRIPTIONS)),

    ;

    private final MixinBuilder builder;

    LateMixins(MixinBuilder builder) {
        this.builder = builder.setPhase(Phase.LATE);
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return this.builder;
    }
}
