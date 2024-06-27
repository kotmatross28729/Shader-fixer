package com.kotmatross.shadersfixer.asm;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class ShadersFixerLateMixins implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.shadersfixer.late.json";
    }
    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadMixinConfig(new File(Launch.minecraftHome, configFolder + "mixins.cfg"));

        //don't integrate mixins if there are no suitable mods
        if(!loadedMods.contains("fiskheroes")) {
            ShaderFixerConfig.FixFisksuperheroesShaders = false;
        }
        if(!loadedMods.contains("NotEnoughItems")) {
            ShaderFixerConfig.FixNEIShaders = false;
        }
        if(!loadedMods.contains("Techguns")) {
            ShaderFixerConfig.FixTechgunsShaders = false;
        }
        if(!loadedMods.contains("jinryuubetterrenderaddon") && !loadedMods.contains("jinryuudragonblockc")) {
            ShaderFixerConfig.FixDragonBlockCShaders = false;
        }
        if(!loadedMods.contains("zeldaswordskills")) {
            ShaderFixerConfig.FixZeldaSwordSkillsShaders = false;
        }

        if(!loadedMods.contains("mcheli")) {
            ShaderFixerConfig.FixMcheliOShaders = false;
        }

        if(!loadedMods.contains("rivalrebels")) {
            ShaderFixerConfig.FixRivalRebelsShaders = false;
        }
        if(!loadedMods.contains("Schematica")) {
            ShaderFixerConfig.FixSchematicaShaders = false;
        }
        if(!loadedMods.contains("journeymap")) {
            ShaderFixerConfig.FixJourneymapShaders = false;
        }
        List<String> mixins = new ArrayList<>();

            if (side == MixinEnvironment.Side.CLIENT) {
                ShadersFixer.logger.info("Integrating Shaderfixer late client mixins...");

                if (ShaderFixerConfig.FixFisksuperheroesShaders) {
                    ShadersFixer.logger.info("Trying to integrate fiskheroes mixins...");
                    mixins.add("client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLightning");
                    mixins.add("client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLine");
                    mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatCircles");
                    mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatLines");
                    mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatWireframe");
                    mixins.add("client.FiskHeroes.client.particle.MixinEntitySHSpellWaveFX");
                    mixins.add("client.FiskHeroes.client.render.effect.MixinEffectTentacles");
                    mixins.add("client.FiskHeroes.client.render.entity.effect.MixinRenderEarthCrack");
                    mixins.add("client.FiskHeroes.client.render.entity.effect.MixinRenderGravityWave");
                    mixins.add("client.FiskHeroes.client.render.entity.effect.MixinRenderSpeedBlur");
                    mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderEnergyBolt");
                    mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderGrapplingHook");
                    mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderSonicWave");
                    mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderSpellWhip");
                    mixins.add("client.FiskHeroes.client.render.entity.MixinRenderGrappleRope");
                    mixins.add("client.FiskHeroes.client.render.projectile.MixinBulletProjectileRenderer");
                    mixins.add("client.FiskHeroes.client.render.projectile.MixinProjectileRenderHandler");
                    mixins.add("client.FiskHeroes.client.render.tile.MixinRenderSuitFabricator");
                    mixins.add("client.FiskHeroes.client.render.tile.MixinRenderSuitDatabase");
                    mixins.add("client.FiskHeroes.client.MixinSHRenderHooks");
                }
                if (ShaderFixerConfig.FixNEIShaders) {
                    ShadersFixer.logger.info("Trying to integrate NotEnoughItems mixins...");
                    mixins.add("client.NotEnoughItems.client.MixinWorldOverlayRenderer");
                }
                if (ShaderFixerConfig.FixTechgunsShaders) {
                    ShadersFixer.logger.info("Trying to integrate Techguns mixins...");
                    mixins.add("client.Techguns.client.renderer.tileentity.MixinRenderTGChest");
                    mixins.add("client.Techguns.client.renderer.MixinTGRenderHelper");
                    mixins.add("client.Techguns.utils.MixinEntityDeathUtils");
//                    mixins.add("client.Techguns.utils.MixinDeathEffect");
//                    mixins.add("client.Techguns.utils.MixinRenderFlyingGibs");

                }
                if (ShaderFixerConfig.FixDragonBlockCShaders) {
                    ShadersFixer.logger.info("Trying to integrate DragonBlockC mixins...");
                    mixins.add("client.DragonBlockC.client.MixinRenderPlayerJBRA");
                    mixins.add("client.DragonBlockC.client.MixinRenderAura");
                    mixins.add("client.DragonBlockC.client.MixinRenderAura2");
                    mixins.add("client.DragonBlockC.client.MixinRenderDBC");
                }
                if (ShaderFixerConfig.FixZeldaSwordSkillsShaders) {
                    ShadersFixer.logger.info("Trying to integrate ZeldaSwordSkills mixins...");
                    mixins.add("client.Zeldaswordskills.client.render.entity.MixinRenderEntityWhip");
                }
                if (ShaderFixerConfig.FixMcheliOShaders) {
                    ShadersFixer.logger.info("Trying to integrate Mcheli Overdrive mixins...");
                    //mixins.add("client.mchelio.MixinMCH_Gui"); //x
                    mixins.add("client.mchelio.MixinMCH_GuiTargetMarker"); //?
                    mixins.add("client.mchelio.MixinMCH_HudItem"); //?
                    mixins.add("client.mchelio.MixinMCH_RenderAircraft"); //<--
                }

                if (ShaderFixerConfig.FixRivalRebelsShaders) {
                    ShadersFixer.logger.info("Trying to integrate Rival Rebels mixins...");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderLaserBurst");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderLaserLink");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderLightningBolt2");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderLightningLink");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderPlasmoid");
                    mixins.add("client.rivalrebels.client.model.MixinModelAstroBlasterBody");
                    mixins.add("client.rivalrebels.client.model.MixinAstroBlasterRenderer");
                    mixins.add("client.rivalrebels.client.model.MixinModelBlastRing");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderSphereBlast");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderTachyonBombBlast");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderTheoreticalTsarBlast");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderTsarBlast");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderAntimatterBombBlast");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderRhodes");
                    mixins.add("client.rivalrebels.client.entity.MixinTileEntityForceFieldNodeRenderer");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderLibrary");
                    mixins.add("client.rivalrebels.client.entity.MixinRenderBlood");
                    mixins.add("client.rivalrebels.client.entity.MixinEntityBloodFX");

                    //mixins.add("client.rivalrebels.client.");
                }
                if (ShaderFixerConfig.FixSchematicaShaders) {
                    ShadersFixer.logger.info("Trying to integrate Schematica mixins...");
                    mixins.add("client.Schematica.client.MixinRendererSchematicChunk");
                    mixins.add("client.Schematica.client.MixinRendererSchematicGlobal");
                    mixins.add("client.Schematica.client.MixinRenderHelper");
                }

                if (ShaderFixerConfig.FixJourneymapShaders) {
                    ShadersFixer.logger.info("Trying to integrate Journeymap mixins...");
                    mixins.add("client.Journeymap.MixinDrawUtil");
                }
            }

        return mixins;
    }
}
