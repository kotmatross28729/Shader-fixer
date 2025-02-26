package com.kotmatross.shadersfixer.asm;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.hbm.config.ClientConfig;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.io.File;
import java.lang.reflect.Method;
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

    public static boolean oldNEI = false;
    public static boolean oldCCC = false;
    public static boolean specjork = false;

    public static boolean ishbmLoaded = false;

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
        if(!loadedMods.contains("Avaritia")) {
            ShaderFixerConfig.FixAvaritiaShaders = false;
        }
        if(!loadedMods.contains("ThaumicConcilium")) {
            ShaderFixerConfig.FixThaumicConciliumShaders = false;
        }
        if(!loadedMods.contains("OpenComputers")) {
            ShaderFixerConfig.FixOpenComputersShaders = false;
        }
        if(!loadedMods.contains("Eln")) {
            ShaderFixerConfig.FixElnShaders = false;
        }
        if(!loadedMods.contains("hbm")) {
            ShaderFixerConfig.FixHbmShaders = false;
        }
        if(!loadedMods.contains("dsurround")) {
            ShaderFixerConfig.FixDSShaders = false;
        }
        if(!loadedMods.contains("lmmx")) {
            ShaderFixerConfig.FixLMMEShaders = false;
        }
        if(!loadedMods.contains("HardcoreEnderExpansion")) {
            ShaderFixerConfig.FixHEEhaders = false;
        }
        if(!loadedMods.contains("angelica")) {
            ShaderFixerConfig.PatchHBMAngelica = false;
        }
        if(!loadedMods.contains("customnpcs")) {
            ShaderFixerConfig.FixCNPCShaders = false;
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
                //PIZDEC FULL
                if (ShaderFixerConfig.FixNEIShaders) {
                   if (Loader.isModLoaded("NotEnoughItems")) {
                       if(Loader.instance().getIndexedModList().get("NotEnoughItems").getVersion().equals("1.0.5.120")) {
                           oldNEI = true;
                       } else {
                           String[] NEIVersionCurrent = Loader.instance().getIndexedModList().get("NotEnoughItems").getVersion().split("\\.");
                           String[] NEIVersionConst = "1.0.5.120".split("\\.");
                           for (int pos = 0; pos < (Math.min(NEIVersionCurrent.length, NEIVersionConst.length)); pos++) {
                               try {
                                   int NEIVersionCurrentPart = Integer.parseInt(NEIVersionCurrent[pos]);
                                   int NEIVersionConstPart = Integer.parseInt(NEIVersionConst[pos]);
                                   if (NEIVersionCurrentPart > NEIVersionConstPart) {
                                       break;
                                   }
                                   if (NEIVersionCurrentPart < NEIVersionConstPart) {
                                       oldNEI = true;
                                       break;
                                   }
                               } catch (NumberFormatException ignored) {}
                           }
                       }
                   }
                    if (Loader.isModLoaded("CodeChickenCore")) {
                        if(Loader.instance().getIndexedModList().get("CodeChickenCore").getVersion().equals("1.0.7.48")) {
                            oldCCC = true;
                        } else {
                            String[] CCCVersionCurrent = Loader.instance().getIndexedModList().get("CodeChickenCore").getVersion().split("\\.");
                            String[] CCCVersionConst = "1.0.7.48".split("\\.");
                            for (int pos = 0; pos < (Math.min(CCCVersionCurrent.length, CCCVersionConst.length)); pos++) {
                                try {
                                int CCCVersionCurrentPart = Integer.parseInt(CCCVersionCurrent[pos]);
                                int CCCVersionConstPart = Integer.parseInt(CCCVersionConst[pos]);
                                if (CCCVersionCurrentPart > CCCVersionConstPart) {
                                    break;
                                }
                                if (CCCVersionCurrentPart < CCCVersionConstPart) {
                                    oldCCC = true;
                                    break;
                                }
                                } catch (NumberFormatException ignored) {}
                            }
                        }
                    }
                    ShadersFixer.logger.info("Trying to integrate NotEnoughItems mixins...");
                    if(!oldNEI){
                        mixins.add("client.NotEnoughItems.client.MixinWorldOverlayRenderer");
                    } else {
                        ShadersFixer.logger.warn("old NEI detected, mixin may be unstable!");
                        mixins.add("client.NotEnoughItems.client.MixinWorldOverlayRendererLEGACY");
                    }
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
                    mixins.add("client.Zeldaswordskills.client.render.entity.MixinRenderEntityHookShot");
                }
                if (ShaderFixerConfig.FixMcheliOShaders) {
                    ShadersFixer.logger.info("Trying to integrate Mcheli Overdrive mixins...");
                    mixins.add("client.mchelio.MixinMCH_GuiTargetMarker");
                    mixins.add("client.mchelio.MixinMCH_HudItem");
                    mixins.add("client.mchelio.MixinMCH_RenderAircraft");
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
                    mixins.add("client.rivalrebels.client.entity.MixinTileEntityPlasmaExplosionRenderer");
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
                if (ShaderFixerConfig.FixAvaritiaShaders) {
                    ShadersFixer.logger.info("Trying to integrate Avaritia mixins...");
                    mixins.add("client.avaritia.client.MixinCosmicItemRenderer");
                    mixins.add("client.avaritia.client.MixinModelArmorInfinity");
                    mixins.add("client.avaritia.client.MixinRenderHeavenArrow");
                }
                if (ShaderFixerConfig.FixThaumicConciliumShaders) {
                    ShadersFixer.logger.info("Trying to integrate Thaumic Concilium mixins...");
                    mixins.add("client.ThaumicConcilium.client.MixinAstralMonitorRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinCrimsonOrbEntityRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinDissolvedRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinDistortionEffectRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinHexOfPredictabilityTileRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinMaterialPeelerRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinQuicksilverElementalRenderer");
                    mixins.add("client.ThaumicConcilium.client.MixinRiftRenderer");
                }
                if (ShaderFixerConfig.FixOpenComputersShaders) {
                    ShadersFixer.logger.info("Trying to integrate OpenComputers mixins...");
                    mixins.add("client.oc.client.MixinScreenRenderer");
                    mixins.add("client.oc.client.MixinHologramRenderer");
                    mixins.add("client.oc.client.MixinRobotRenderer");
                    mixins.add("client.oc.client.MixinRenderState");
                }
                if (ShaderFixerConfig.FixElnShaders) {
                    ShadersFixer.logger.info("Trying to integrate ElectricalAge mixins...");
                    mixins.add("client.eln.client.MixinDataLogs");
                    mixins.add("client.eln.client.MixinUtilsClient");
                    if(ShaderFixerConfig.ElnLightMixins) {
                        mixins.add("client.eln.client.MixinLampSocketStandardObjRender");
                        mixins.add("client.eln.client.MixinLampSocketSuspendedObjRender");
                    }
                    mixins.add("client.eln.client.MixinNixieTubeDescriptor");
                }

                if (ShaderFixerConfig.FixHbmShaders) {
                    try {
                        Class.forName("com.hbm.dim.SolarSystem"); //idk why this, but why not?
                        specjork = true;
                    } catch (ClassNotFoundException e) {
                        specjork = false;
                    }

                    ishbmLoaded = Loader.isModLoaded("hbm");

                    ShadersFixer.logger.info("Trying to integrate Hbm's NTM mixins...");
                    mixins.add("client.hbm.client.MixinParticleAmatFlash"); //Antimatter explosion
                    mixins.add("client.hbm.client.MixinParticleDebugLine"); //Drone path lines
                    mixins.add("client.hbm.client.MixinParticleRadiationFog"); //GL11.GL_LIGHTING
                    mixins.add("client.hbm.client.MixinParticleSpark"); //"Tauon" turret, arc welder
                    mixins.add("client.hbm.client.MixinParticleSpentCasing"); //Smoke on bullets/shells
                    if(specjork) {
                        mixins.add("client.hbm.client.MixinBeamPronter"); //FEL laser, ICF laser
                    } else {
                        mixins.add("client.hbm.client.MixinBeamPronterORIG"); //FEL laser, ICF laser
                    }
                    mixins.add("client.hbm.client.MixinItemRenderDetonatorLaser"); //LED on top, sine wave on the side
                    mixins.add("client.hbm.client.MixinItemRendererMeteorSword"); //Lighting glitch in 3rd person view
                    mixins.add("client.hbm.client.MixinItemRenderLibrary"); //Radiation-Powered Engine lights render in hand
                    mixins.add("client.hbm.client.MixinModelArmorEnvsuit"); //Armor helmet lamps
                    mixins.add("client.hbm.client.MixinRenderBAT9000"); //Liquid inside
                    mixins.add("client.hbm.client.MixinRenderBeam"); //Immolator/ HPP LaserJet projectile
                    mixins.add("client.hbm.client.MixinRenderBeam2"); //Power Fist (Zapper) projectile
                    mixins.add("client.hbm.client.MixinRenderBeam3"); //Power Fist (Extracting Mining Laser) projectile
                    mixins.add("client.hbm.client.MixinRenderBeam4"); //Spark Plug projectile
                    mixins.add("client.hbm.client.MixinRenderBeam5"); //B92 projectile
                    mixins.add("client.hbm.client.MixinRenderBeam6"); //B92 projectile
                    mixins.add("client.hbm.client.MixinRenderBlackHole"); //Jet from the center along the poles
                    mixins.add("client.hbm.client.MixinRenderBobble"); //Pu-238 Bobble
                    mixins.add("client.hbm.client.MixinRenderCloudRainbow"); //Flickering field, during a DFC explosion, from a B93 projectile
                    mixins.add("client.hbm.client.MixinRenderCore"); //Sphere of the block itself, additional spheres when it works
                    mixins.add("client.hbm.client.MixinRenderCraneConsole"); //Buttons on the console
                    mixins.add("client.hbm.client.MixinRenderDeathBlast"); //From the orbital death ray
                    mixins.add("client.hbm.client.MixinRenderDemonLamp"); //Disk of blue light, brighter with shaders
                    mixins.add("client.hbm.client.MixinRenderFlare"); //Flare grenade, brighter with shaders
                    mixins.add("client.hbm.client.MixinRenderFOEQ"); //Fire behind the falling shuttle
                    mixins.add("client.hbm.client.MixinRenderLantern"); //Light in the lamp
                    mixins.add("client.hbm.client.MixinRenderLanternINNER"); //Light in the lamp
                    mixins.add("client.hbm.client.MixinRenderLanternBehemoth"); //Light in the lamp
                    mixins.add("client.hbm.client.MixinRenderMachineForceField"); //Green lines forming a sphere
                    mixins.add("client.hbm.client.MixinRenderMixer"); //Liquid inside
                    mixins.add("client.hbm.client.MixinRenderOverhead"); //Red square near entities
                    mixins.add("client.hbm.client.MixinRenderPumpjack"); //Pumpjack bridle
                    mixins.add("client.hbm.client.MixinRenderRadarScreen"); //Green bar going from top to bottom
                    mixins.add("client.hbm.client.MixinRenderRadGen"); //2 lights on top of engine
                    mixins.add("client.hbm.client.MixinRenderRBMKConsole"); //Buttons on the console
                    mixins.add("client.hbm.client.MixinRenderRBMKLid"); //Cherenkov radiation
                    mixins.add("client.hbm.client.MixinRenderSiegeCraft"); //4 lights on top
                    mixins.add("client.hbm.client.MixinRenderSiegeLaser"); //UFO projectile
                    mixins.add("client.hbm.client.MixinRenderSmallReactor"); //Cherenkov's radiation, brighter with shaders
                    mixins.add("client.hbm.client.MixinRenderSolarBoiler"); //Rays of light
                    mixins.add("client.hbm.client.MixinRenderSparks"); //Lightning in a breeding reactor/DFC
                    mixins.add("client.hbm.client.MixinRenderSpear"); //Rays of light from the digamma spear
                    mixins.add("client.hbm.client.MixinRenderOrbus"); //Liquid inside
                    if(specjork) {
                        mixins.add("client.hbm.client.MixinSkyProviderCelestial");  //Sky
                        mixins.add("client.hbm.client.MixinSkyProviderLaytheSunset"); //"Fix" for angelica
                    }
                    mixins.add("client.hbm.client.MixinRenderBullet"); //!GLASS/TAU
                    mixins.add("client.hbm.client.MixinRenderRainbow"); //!ZOMG

                    mixins.add("client.hbm.client.sedna.MixinLegoClient");
                    mixins.add("client.hbm.client.sedna.MixinModEventHandlerRenderer");
                    mixins.add("client.hbm.client.sedna.MixinItemRenderWeaponBase");

                    mixins.add("client.hbm.client.sedna.guns.MixinItemRenderAtlas");
                    mixins.add("client.hbm.client.sedna.guns.MixinItemRenderDANI");
                    mixins.add("client.hbm.client.sedna.guns.MixinItemRenderHenry");
                    mixins.add("client.hbm.client.sedna.guns.MixinItemRenderMaresleg");
                    mixins.add("client.hbm.client.sedna.guns.MixinItemRenderMareslegAkimbo");

                    mixins.add("client.hbm.client.MixinRenderChemical"); //Antimatter thing
                    mixins.add("client.hbm.client.MixinRenderSolidifier"); //Liquid inside
                    mixins.add("client.hbm.client.MixinRenderLiquefactor"); //Liquid inside
    
                    mixins.add("client.hbm.client.MixinRenderRefueler");
                    mixins.add("client.hbm.client.MixinModelNo9"); //Lamp
                    mixins.add("client.hbm.client.MixinRenderCharger"); //Lamp
                    mixins.add("client.hbm.client.MixinRenderFurnaceSteel"); //Heat thing
                    mixins.add("client.hbm.client.MixinModelArmorWingsPheo"); //idk
    
                    mixins.add("client.hbm.client.MixinRenderTorex");
                    mixins.add("client.hbm.client.MixinDiamondPronter");
                    
                    if(ShaderFixerConfig.HbmExtendedHazardDescriptions) {
                        mixins.add("client.hbm.client.descr.MixinHazardTypeAsbestos");
                        mixins.add("client.hbm.client.descr.MixinHazardTypeBlinding");
                        mixins.add("client.hbm.client.descr.MixinHazardTypeCoal");
                        mixins.add("client.hbm.client.descr.MixinHazardTypeExplosive");
                        mixins.add("client.hbm.client.descr.MixinHazardTypeHot");
                        mixins.add("client.hbm.client.descr.MixinHazardTypeHydroactive");
                    }
                }
                
                if (ShaderFixerConfig.FixDSShaders) {
                    ShadersFixer.logger.info("Trying to integrate DynamicSurroundings mixins...");
                    mixins.add("client.DynamicSurroundings.client.MixinAuroraRenderer");
                }

                if (ShaderFixerConfig.FixLMMEShaders) {
                    ShadersFixer.logger.warn("Trying to integrate LittleMaidMobEnhanced mixins...");
                    mixins.add("client.lmme.MixinGuiInventory");
                }
                if (ShaderFixerConfig.FixHEEhaders) {
                    ShadersFixer.logger.info("Trying to integrate HardcoreEnderExpansion mixins...");
                    mixins.add("client.HEE.MixinRenderBossDragon");
                    mixins.add("client.HEE.MixinRenderWeatherLightningBoltPurple");
                    mixins.add("client.HEE.MixinModClientProxy");
                }
                
                if (ShaderFixerConfig.PatchHBMAngelica) {
                    ShadersFixer.logger.info("Trying to integrate Angelica mixins...");
                    mixins.add("client.angelica.MixinHandRenderer");
                }
    
                if (ShaderFixerConfig.FixCNPCShaders) {
                    ShadersFixer.logger.info("Trying to integrate CustomNPC mixins...");
                    mixins.add("client.cnpc.MixinRenderChatMessages");
                    mixins.add("client.cnpc.MixinRenderNPCInterface");
                }
            }

        return mixins;
    }

    public static void handleInterpolation(float interp) {
        if (ishbmLoaded) {
            try {
                ItemRenderWeaponBase.interp = interp;
            } catch (NoSuchFieldError ignored){}
        }
    }
    public static float getGunsSwayMagnitude(ItemStack stack) {
        return invokeHbmRenderGetters(stack, "getSwayMagnitude");
    }
    public static float getGunsSwayPeriod(ItemStack stack) {
        return invokeHbmRenderGetters(stack, "getSwayPeriod");
    }
    public static float getGunsTurnMagnitude(ItemStack stack) {
        return invokeHbmRenderGetters(stack, "getTurnMagnitude");
    }

    public static float getGunsBaseFOV(ItemStack stack) {
        return invokeHbmRenderGetters(stack, "getBaseFOV");
    }

    public static boolean getFOVConf() {
        if (ishbmLoaded) {
            try {
                return ClientConfig.GUN_MODEL_FOV.get();
            } catch (NoSuchFieldError ignored){
            }
        }
        return false;
    }

    //Because "protected"
    public static float invokeHbmRenderGetters(ItemStack stack, String name) {
        if (ishbmLoaded && stack != null) {
            try {
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
                if(customRenderer instanceof ItemRenderWeaponBase) {
                    Method method = ItemRenderWeaponBase.class.getDeclaredMethod(name, ItemStack.class);
                    method.setAccessible(true);
                    return (float) method.invoke(customRenderer, stack);
                }
            } catch (Exception ignored) {}
        }
        return name.equals("getSwayMagnitude") ? 0.5F : name.equals("getSwayPeriod") ? 0.75F : name.equals("getTurnMagnitude") ? 2.75F : name.equals("getBaseFOV") ? 70.0F: 0;
    }
}
