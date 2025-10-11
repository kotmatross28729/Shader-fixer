package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.kotmatross.shaderfixer.asm.plugin.ShaderFixerLateMixins;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@SuppressWarnings("unused")
public enum LateMixins implements IMixins {

    ANGELICA(new MixinBuilder("Patches HandRenderer to work correctly with HBM's NTM gunfix")
        .addRequiredMod(TargetMods.ANGELICA)
        .addRequiredMod(TargetMods.HBM)
        .addClientMixins("ANGELICA.MixinHandRenderer")),

    AVARITIA(new MixinBuilder().addRequiredMod(TargetMods.AVARITIA)
        .addClientMixins(
            "AVARITIA.MixinCosmicRenderShenanigans",
            "AVARITIA.MixinModelArmorInfinity",
            "AVARITIA.MixinRenderHeavenArrow")),

    BACKHAND(new MixinBuilder(
        "Cancel rendering of the offhand item if item in the main hand is NTM gun (otherwise it causes rendering bugs)")
            .addRequiredMod(TargetMods.BACKHAND)
            .addRequiredMod(TargetMods.HBM)
            .addClientMixins("BACKHAND.MixinBackhandRenderHelper", "BACKHAND.MixinItemRendererHooks")
            .setApplyIf(() -> ShaderFixerConfig.NTM_GUNFIX)),

    CUSTOMNPCS(new MixinBuilder().addRequiredMod(TargetMods.CUSTOMNPCS)
        .addClientMixins("CUSTOMNPCS.MixinRenderChatMessages", "CUSTOMNPCS.MixinRenderNPCInterface")),

    CUSTOMPLAYERMODELS(new MixinBuilder().addRequiredMod(TargetMods.CUSTOMPLAYERMODELS)
        .addClientMixins("CUSTOMPLAYERMODELS.MixinClientProxy")),

    CUSTOMPLAYERMODELS_EXP_FIX(new MixinBuilder().addRequiredMod(TargetMods.CUSTOMPLAYERMODELS)
        .addClientMixins("CUSTOMPLAYERMODELS.MixinRenderStage")
        .setApplyIf(() -> ShaderFixerConfig.CPM_EXP_FIX)),

    DBC(new MixinBuilder().addRequiredMod(TargetMods.DBCR)
        .addRequiredMod(TargetMods.DBC)
        .addClientMixins(
            "DBC.MixinRenderPlayerJBRA",
            "DBC.MixinRenderAura",
            "DBC.MixinRenderAura2",
            "DBC.MixinRenderDBC")),

    DSURROUND(new MixinBuilder().addRequiredMod(TargetMods.DSURROUND)
        .addClientMixins("DSURROUND.MixinAuroraRenderer")),

    ELN(new MixinBuilder().addRequiredMod(TargetMods.ELN)
        .addClientMixins("ELN.MixinDataLogs", "ELN.MixinUtilsClient", "ELN.MixinNixieTubeDescriptor")),

    FINDIT(new MixinBuilder().addRequiredMod(TargetMods.FINDIT)
        .addClientMixins("FINDIT.MixinBlockHighlighter", "FINDIT.MixinEntityHighlighter")),

    FISKHEROES(new MixinBuilder().addRequiredMod(TargetMods.FISKHEROES)
        .addClientMixins(
            "FISKHEROES.pack.json.beam.MixinBeamRendererLaser",
            "FISKHEROES.pack.json.beam.MixinBeamRendererLightning",
            "FISKHEROES.pack.json.beam.MixinBeamRendererLine",
            "FISKHEROES.pack.json.shape.MixinShapeFormatCircles",
            "FISKHEROES.pack.json.shape.MixinShapeFormatLines",
            "FISKHEROES.pack.json.shape.MixinShapeFormatWireframe",
            "FISKHEROES.particle.MixinEntitySHSpellWaveFX",
            "FISKHEROES.render.effect.MixinEffectTentacles",
            "FISKHEROES.render.entity.effect.MixinRenderEarthCrack",
            "FISKHEROES.render.entity.effect.MixinRenderGravityWave",
            "FISKHEROES.render.entity.effect.MixinRenderSpeedBlur",
            "FISKHEROES.render.entity.projectile.MixinRenderEnergyBolt",
            "FISKHEROES.render.entity.projectile.MixinRenderGrapplingHook",
            "FISKHEROES.render.entity.projectile.MixinRenderSonicWave",
            "FISKHEROES.render.entity.projectile.MixinRenderSpellWhip",
            "FISKHEROES.render.entity.MixinRenderGrappleRope",
            "FISKHEROES.render.projectile.MixinBulletProjectileRenderer",
            "FISKHEROES.render.projectile.MixinProjectileRenderHandler",
            "FISKHEROES.render.tile.MixinRenderSuitFabricator",
            "FISKHEROES.render.tile.MixinRenderSuitDatabase",
            "FISKHEROES.MixinSHRenderHooks")),

    HBM(new MixinBuilder().addRequiredMod(TargetMods.HBM)
        .addClientMixins(
            "HBM.MixinParticleAmatFlash", // Antimatter explosion
            "HBM.MixinParticleDebugLine", // Drone path lines
            "HBM.MixinParticleRadiationFog", // GL11.GL_LIGHTING
            "HBM.MixinParticleSpark", // "Tauon" turret, arc welder
            "HBM.MixinParticleSpentCasing", // Smoke on bullets/shells
            "HBM.MixinItemRenderDetonatorLaser", // LED on top, sine wave on the side
            "HBM.MixinItemRendererMeteorSword", // Lighting glitch in 3rd person view
            "HBM.MixinItemRenderLibrary", // Radiation-Powered Engine lights render in hand
            "HBM.MixinModelArmorEnvsuit", // Armor helmet lamps
            "HBM.MixinRenderBAT9000", // Liquid inside
            "HBM.MixinRenderBeam", // Immolator/ HPP LaserJet projectile
            "HBM.MixinRenderBeam2", // Power Fist (Zapper) projectile
            "HBM.MixinRenderBeam3", // Power Fist (Extracting Mining Laser) projectile
            "HBM.MixinRenderBeam4", // Spark Plug projectile
            "HBM.MixinRenderBeam5", // B92 projectile
            "HBM.MixinRenderBeam6", // B92 projectile
            "HBM.MixinRenderBlackHole", // Jet from the center along the poles
            "HBM.MixinRenderBobble", // Pu-238 Bobble
            "HBM.MixinRenderCloudRainbow", // Flickering field, during a DFC explosion, from a B93 projectile
            "HBM.MixinRenderCore", // Sphere of the block itself, additional spheres when it works
            "HBM.MixinRenderCraneConsole", // Buttons on the console
            "HBM.MixinRenderDeathBlast", // From the orbital death ray
            "HBM.MixinRenderDemonLamp", // Disk of blue light, brighter with shaders
            "HBM.MixinRenderFlare", // Flare grenade, brighter with shaders
            "HBM.MixinRenderFOEQ", // Fire behind the falling shuttle
            "HBM.MixinRenderLantern", // Light in the lamp
            "HBM.MixinRenderLanternINNER", // Light in the lamp
            "HBM.MixinRenderLanternBehemoth", // Light in the lamp
            "HBM.MixinRenderMachineForceField", // Green lines forming a sphere
            "HBM.MixinRenderMixer", // Liquid inside
            "HBM.MixinRenderOverhead", // Red square near entities
            "HBM.MixinRenderPumpjack", // Pumpjack bridle
            "HBM.MixinRenderRadarScreen", // Green bar going from top to bottom
            "HBM.MixinRenderRadGen", // 2 lights on top of engine
            "HBM.MixinRenderRBMKConsole", // Buttons on the console
            "HBM.MixinRenderRBMKLid", // Cherenkov radiation
            "HBM.MixinRenderSiegeCraft", // 4 lights on top
            "HBM.MixinRenderSiegeLaser", // UFO projectile
            "HBM.MixinRenderSmallReactor", // Cherenkov's radiation, brighter with shaders
            "HBM.MixinRenderSolarBoiler", // Rays of light
            "HBM.MixinRenderSparks", // Lightning in a breeding reactor/DFC
            "HBM.MixinRenderSpear", // Rays of light from the digamma spear
            "HBM.MixinRenderOrbus", // Liquid inside
            "HBM.MixinRenderBullet", // !GLASS/TAU
            "HBM.MixinRenderRainbow", // !ZOMG
            "HBM.sedna.MixinLegoClient", // Bullet render
            "HBM.sedna.MixinModEventHandlerRenderer", // Redirect to our system
            "HBM.sedna.MixinItemRenderWeaponBase", // Smoke, flash brightness
            "HBM.sedna.guns.MixinItemRenderAtlas", // Patch texture after smoke
            "HBM.sedna.guns.MixinItemRenderDANI", // Patch dual texture
            "HBM.sedna.guns.MixinItemRenderHenry", // Patch texture after smoke
            "HBM.sedna.guns.MixinItemRenderMaresleg", // Patch texture after smoke
            "HBM.sedna.guns.MixinItemRenderMareslegAkimbo", // Patch texture after smoke
            "HBM.MixinRenderChemical", // Antimatter thing
            "HBM.MixinRenderSolidifier", // Liquid inside
            "HBM.MixinRenderLiquefactor", // Liquid inside
            "HBM.MixinRenderRefueler", // Liquid & glClipPlane
            "HBM.MixinModelNo9", // Lamp
            "HBM.MixinRenderCharger", // Lamp
            "HBM.MixinRenderFurnaceSteel", // Heat thing
            "HBM.MixinModelArmorWingsPheo", // idk (I know)
            "HBM.MixinRenderTorex", // MUSHROOM
            "HBM.MixinDiamondPronter", // NFPA 704 (on barrels, tanks)
            "HBM.MixinRenderPylonBase", // WIRES
            "HBM.MixinHbmAnimations", // Fix flickering
            "HBM.MixinBlockRebar", // Fix outline
            "HBM.sedna.guns.MixinItemRenderNI4NI", // Coins display
            "HBM.MixinArmorEnvsuit" // Armor helmet lamps
        )),

    HBM_OG_ONLY(new MixinBuilder().addRequiredMod(TargetMods.HBM)
        .addExcludedMod(TargetMods.HBM_SPACE)
        .addClientMixins("HBM.MixinBeamPronter") // LASERS
    ),

    HBM_SPACE(new MixinBuilder().addRequiredMod(TargetMods.HBM_SPACE)
        .addClientMixins(
            "HBM.space.MixinBeamPronter_SPACE", // LASERS, but for spork
            "HBM.space.MixinSkyProviderCelestial", // Sky
            "HBM.space.MixinMissilePronter", // "Fix" (just scale it a little) fuselage Z-fighting (glClipPlane not
                                             // working with shaders)
            "HBM.space.MixinOrreryPronter" // Yare yare... (Fix orbital paths)
        )),

    HEE(new MixinBuilder().addRequiredMod(TargetMods.HEE)
        .addClientMixins(
            "HEE.MixinRenderBossDragon",
            "HEE.MixinRenderWeatherLightningBoltPurple",
            "HEE.MixinModClientProxy")),

    JOURNEYMAP(new MixinBuilder().addRequiredMod(TargetMods.JOURNEYMAP)
        .addClientMixins("JOURNEYMAP.MixinDrawUtil")),

    LMMX(new MixinBuilder().addRequiredMod(TargetMods.LMMX)
        .addClientMixins("LMMX.MixinGuiInventory")),

    MANEUVERGEAR(new MixinBuilder().addRequiredMod(TargetMods.MANEUVERGEAR)
        .addClientMixins("MANEUVERGEAR.MixinRenderEntityDart")),

    MAPLETREE(new MixinBuilder().addRequiredMod(TargetMods.MAPLETREE)
        .addClientMixins(
            "MAPLETREE.Mixin_ecru_TileEntityLighthouseIlluminationRender",
            "MAPLETREE.Mixin_ecru_TileEntitySLightRender")),

    MCHELI(new MixinBuilder().addRequiredMod(TargetMods.MCHELI)
        .addClientMixins(
            "MCHELI.MixinMCH_GuiTargetMarker",
            "MCHELI.MixinMCH_HudItem",
            "MCHELI.MixinMCH_RenderAircraft")),

    NEI(new MixinBuilder().addRequiredMod(TargetMods.NEI)
        .addClientMixins("NEI.MixinWorldOverlayRenderer")
        .setApplyIf(() -> !ShaderFixerLateMixins.OLD_NEI_LOADED)),

    NEI_OG(new MixinBuilder().addRequiredMod(TargetMods.NEI)
        .addClientMixins("NEI.MixinWorldOverlayRendererLEGACY")
        .setApplyIf(() -> ShaderFixerLateMixins.OLD_NEI_LOADED)),

    OPENCOMPUTERS(new MixinBuilder().addRequiredMod(TargetMods.OPENCOMPUTERS)
        .addClientMixins(
            "OPENCOMPUTERS.MixinScreenRenderer",
            "OPENCOMPUTERS.MixinHologramRenderer",
            "OPENCOMPUTERS.MixinRobotRenderer",
            "OPENCOMPUTERS.MixinRenderState")),

    RIVALREBELS(new MixinBuilder().addRequiredMod(TargetMods.RIVALREBELS)
        .addClientMixins(
            "RIVALREBELS.entity.MixinRenderLaserBurst",
            "RIVALREBELS.entity.MixinRenderLaserLink",
            "RIVALREBELS.entity.MixinRenderLightningBolt2",
            "RIVALREBELS.entity.MixinRenderLightningLink",
            "RIVALREBELS.entity.MixinRenderPlasmoid",
            "RIVALREBELS.model.MixinModelAstroBlasterBody",
            "RIVALREBELS.model.MixinAstroBlasterRenderer",
            "RIVALREBELS.model.MixinModelBlastRing",
            "RIVALREBELS.entity.MixinRenderSphereBlast",
            "RIVALREBELS.entity.MixinRenderTachyonBombBlast",
            "RIVALREBELS.entity.MixinRenderTheoreticalTsarBlast",
            "RIVALREBELS.entity.MixinRenderTsarBlast",
            "RIVALREBELS.entity.MixinRenderAntimatterBombBlast",
            "RIVALREBELS.entity.MixinRenderRhodes",
            "RIVALREBELS.entity.MixinTileEntityForceFieldNodeRenderer",
            "RIVALREBELS.entity.MixinRenderLibrary",
            "RIVALREBELS.entity.MixinRenderBlood",
            "RIVALREBELS.entity.MixinEntityBloodFX",
            "RIVALREBELS.entity.MixinTileEntityPlasmaExplosionRenderer")),

    SCHEMATICA(new MixinBuilder().addRequiredMod(TargetMods.SCHEMATICA)
        .addClientMixins(
            "SCHEMATICA.MixinRendererSchematicChunk",
            "SCHEMATICA.MixinRendererSchematicGlobal",
            "SCHEMATICA.MixinRenderHelper")),

    SIGNPIC(new MixinBuilder().addRequiredMod(TargetMods.SIGNPIC)
        .addClientMixins(
            "SIGNPIC.MixinCustomTileEntitySignRenderer",
            "SIGNPIC.MixinRenderHelper",
            "SIGNPIC.MixinStateRender")),

    TECHGUNS(new MixinBuilder().addRequiredMod(TargetMods.TECHGUNS)
        .addClientMixins("TECHGUNS.MixinRenderTGChest", "TECHGUNS.MixinTGRenderHelper")),

    WEAPONMOD(new MixinBuilder().addRequiredMod(TargetMods.WEAPONMOD)
        .addClientMixins("WEAPONMOD.MixinRenderFlail")),

    ZELDASWORDSKILLS(new MixinBuilder().addRequiredMod(TargetMods.ZELDASWORDSKILLS)
        .addClientMixins("ZELDASWORDSKILLS.MixinRenderEntityWhip", "ZELDASWORDSKILLS.MixinRenderEntityHookShot")),

    // TWEAKS
    FISKHEROES_CPM_COMPAT(new MixinBuilder().addRequiredMod(TargetMods.FISKHEROES)
        .addClientMixins("FISKHEROES.CPMCompat.MixinModelBipedMultiLayer")
        .setApplyIf(() -> ShaderFixerConfig.FISKHEROES_CPM_COMPAT)),

    ELN_LIGHT(new MixinBuilder().addRequiredMod(TargetMods.ELN)
        .addClientMixins("ELN.MixinLampSocketStandardObjRender", "ELN.MixinLampSocketSuspendedObjRender")
        .setApplyIf(() -> ShaderFixerConfig.ELN_LIGHT_MIXINS)),

    HBM_EXTENDED_HAZARD(new MixinBuilder().addRequiredMod(TargetMods.HBM)
        .addClientMixins(
            "HBM.descr.MixinHazardTypeAsbestos",
            "HBM.descr.MixinHazardTypeBlinding",
            "HBM.descr.MixinHazardTypeCoal",
            "HBM.descr.MixinHazardTypeExplosive",
            "HBM.descr.MixinHazardTypeHot",
            "HBM.descr.MixinHazardTypeHydroactive")
        .setApplyIf(() -> ShaderFixerConfig.HBM_EXTENDED_HAZARD_DESCRIPTIONS)),

    HBM_FLASH_DEPTH(new MixinBuilder().addRequiredMod(TargetMods.HBM)
        .addClientMixins("HBM.sedna.MixinItemRenderWeaponBase_DEPTH")
        .setApplyIf(() -> ShaderFixerConfig.HBM_MUZZLE_FLASH_ENABLE_DEPTH));

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
