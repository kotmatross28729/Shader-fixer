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
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

@LateMixin
public class ShadersFixerLateMixins implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.shadersfixer.late.json";
    }
    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();

    public static boolean oldNEI = false;
    public static boolean oldCCC = false;
    public static boolean oldAvaritia = false;
    public static boolean specjork = false;
    public static boolean ishbmLoaded = false;

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        ShadersFixer.logger.info("Integrating Shaderfixer late mixins...");
        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadMixinConfig(new File(Launch.minecraftHome, configFolder + "mixins.cfg"));

        oldNEI = checkNEIVersion();
        oldCCC = checkCCCVersion();
        oldAvaritia = checkAvaritiaVersion();
        try {
            specjork = (Launch.classLoader.getClassBytes("com.hbm.dim.SolarSystem") != null);
        } catch (IOException e) {
            specjork = false;
        }
        ishbmLoaded = Loader.isModLoaded("hbm");
    
//        long startTime = System.nanoTime();
        
        Map<String, Consumer<Boolean>> modConfigMap = new HashMap<>();
    
        modConfigMap.put("fiskheroes", value -> ShaderFixerConfig.FixFisksuperheroesShaders = value);
        modConfigMap.put("NotEnoughItems", value -> ShaderFixerConfig.FixNEIShaders = value);
        modConfigMap.put("Techguns", value -> ShaderFixerConfig.FixTechgunsShaders = value);
        modConfigMap.put("jinryuubetterrenderaddon", value -> ShaderFixerConfig.FixDragonBlockCShaders = value);
        modConfigMap.put("jinryuudragonblockc", value -> ShaderFixerConfig.FixDragonBlockCShaders = value);
        modConfigMap.put("zeldaswordskills", value -> ShaderFixerConfig.FixZeldaSwordSkillsShaders = value);
        modConfigMap.put("mcheli", value -> ShaderFixerConfig.FixMcheliOShaders = value);
        modConfigMap.put("rivalrebels", value -> ShaderFixerConfig.FixRivalRebelsShaders = value);
        modConfigMap.put("Schematica", value -> ShaderFixerConfig.FixSchematicaShaders = value);
        modConfigMap.put("journeymap", value -> ShaderFixerConfig.FixJourneymapShaders = value);
        modConfigMap.put("Avaritia", value -> ShaderFixerConfig.FixAvaritiaShaders = value);
        modConfigMap.put("ThaumicConcilium", value -> ShaderFixerConfig.FixThaumicConciliumShaders = value);
        modConfigMap.put("OpenComputers", value -> ShaderFixerConfig.FixOpenComputersShaders = value);
        modConfigMap.put("Eln", value -> ShaderFixerConfig.FixElnShaders = value);
        modConfigMap.put("hbm", value -> ShaderFixerConfig.FixHbmShaders = value);
        modConfigMap.put("dsurround", value -> ShaderFixerConfig.FixDSShaders = value);
        modConfigMap.put("lmmx", value -> ShaderFixerConfig.FixLMMEShaders = value);
        modConfigMap.put("HardcoreEnderExpansion", value -> ShaderFixerConfig.FixHEEhaders = value);
        modConfigMap.put("angelica", value -> ShaderFixerConfig.PatchHBMAngelica = value);
        modConfigMap.put("customnpcs", value -> ShaderFixerConfig.FixCNPCShaders = value);
        
        //ADD THERE ^
        
        modConfigMap.forEach((modId, configSetter) -> {
            boolean isLoaded = loadedMods.contains(modId);
            boolean currentState = isLoaded && getCurrentConfigState(modId);
            configSetter.accept(isLoaded && currentState);
        });
        
        List<String> mixins = new ArrayList<>();

        if (side == MixinEnvironment.Side.CLIENT) {
            for (Object[] fix : getFixes()) {
                if ((boolean) fix[0]) {
                    handleFix(fix[1], mixins);
                }
            }
        }
    
//        long endTime = System.nanoTime();
//        long elapsedTime = (endTime - startTime);

//        ShadersFixer.logger.info("Execution time: {} ms", elapsedTime / 1_000_000);
//        ShadersFixer.logger.info("Execution time: {} ns", elapsedTime);
        
        return mixins;
    }
    
    private boolean getCurrentConfigState(String modId) {
        return switch (modId) {
            case "fiskheroes" -> ShaderFixerConfig.FixFisksuperheroesShaders;
            case "NotEnoughItems" -> ShaderFixerConfig.FixNEIShaders;
            case "Techguns" -> ShaderFixerConfig.FixTechgunsShaders;
            case "jinryuubetterrenderaddon", "jinryuudragonblockc" -> ShaderFixerConfig.FixDragonBlockCShaders;
            case "zeldaswordskills" -> ShaderFixerConfig.FixZeldaSwordSkillsShaders;
            case "mcheli" -> ShaderFixerConfig.FixMcheliOShaders;
            case "rivalrebels" -> ShaderFixerConfig.FixRivalRebelsShaders;
            case "Schematica" -> ShaderFixerConfig.FixSchematicaShaders;
            case "journeymap" -> ShaderFixerConfig.FixJourneymapShaders;
            case "Avaritia" -> ShaderFixerConfig.FixAvaritiaShaders;
            case "ThaumicConcilium" -> ShaderFixerConfig.FixThaumicConciliumShaders;
            case "OpenComputers" -> ShaderFixerConfig.FixOpenComputersShaders;
            case "Eln" -> ShaderFixerConfig.FixElnShaders;
            case "hbm" -> ShaderFixerConfig.FixHbmShaders;
            case "dsurround" -> ShaderFixerConfig.FixDSShaders;
            case "lmmx" -> ShaderFixerConfig.FixLMMEShaders;
            case "HardcoreEnderExpansion" -> ShaderFixerConfig.FixHEEhaders;
            case "angelica" -> ShaderFixerConfig.PatchHBMAngelica;
            case "customnpcs" -> ShaderFixerConfig.FixCNPCShaders;
            
            //ADD THERE ^
            
            default -> false;
        };
    }
    
    private Object[][] getFixes() {
        return new Object[][] {
                {ShaderFixerConfig.FixFisksuperheroesShaders, new String[] {
                        "client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLightning",
                        "client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLine",
                        "client.FiskHeroes.client.pack.json.shape.MixinShapeFormatCircles",
                        "client.FiskHeroes.client.pack.json.shape.MixinShapeFormatLines",
                        "client.FiskHeroes.client.pack.json.shape.MixinShapeFormatWireframe",
                        "client.FiskHeroes.client.particle.MixinEntitySHSpellWaveFX",
                        "client.FiskHeroes.client.render.effect.MixinEffectTentacles",
                        "client.FiskHeroes.client.render.entity.effect.MixinRenderEarthCrack",
                        "client.FiskHeroes.client.render.entity.effect.MixinRenderGravityWave",
                        "client.FiskHeroes.client.render.entity.effect.MixinRenderSpeedBlur",
                        "client.FiskHeroes.client.render.entity.projectile.MixinRenderEnergyBolt",
                        "client.FiskHeroes.client.render.entity.projectile.MixinRenderGrapplingHook",
                        "client.FiskHeroes.client.render.entity.projectile.MixinRenderSonicWave",
                        "client.FiskHeroes.client.render.entity.projectile.MixinRenderSpellWhip",
                        "client.FiskHeroes.client.render.entity.MixinRenderGrappleRope",
                        "client.FiskHeroes.client.render.projectile.MixinBulletProjectileRenderer",
                        "client.FiskHeroes.client.render.projectile.MixinProjectileRenderHandler",
                        "client.FiskHeroes.client.render.tile.MixinRenderSuitFabricator",
                        "client.FiskHeroes.client.render.tile.MixinRenderSuitDatabase",
                        "client.FiskHeroes.client.MixinSHRenderHooks",
                }},
                {ShaderFixerConfig.FixNEIShaders, new String[] {
                    oldNEI ? "client.NotEnoughItems.client.MixinWorldOverlayRendererLEGACY" : "client.NotEnoughItems.client.MixinWorldOverlayRenderer",
                }},
                {ShaderFixerConfig.FixTechgunsShaders, new String[] {
                    "client.Techguns.client.renderer.tileentity.MixinRenderTGChest",
                    "client.Techguns.client.renderer.MixinTGRenderHelper",
                    "client.Techguns.utils.MixinEntityDeathUtils",
                }},
                {ShaderFixerConfig.FixDragonBlockCShaders, new String[] {
                        "client.DragonBlockC.client.MixinRenderPlayerJBRA",
                        "client.DragonBlockC.client.MixinRenderAura",
                        "client.DragonBlockC.client.MixinRenderAura2",
                        "client.DragonBlockC.client.MixinRenderDBC",
                }},
                {ShaderFixerConfig.FixZeldaSwordSkillsShaders, new String[] {
                        "client.Zeldaswordskills.client.render.entity.MixinRenderEntityWhip",
                        "client.Zeldaswordskills.client.render.entity.MixinRenderEntityHookShot",
                }},
                {ShaderFixerConfig.FixMcheliOShaders, new String[] {
                       "client.mchelio.MixinMCH_GuiTargetMarker",
                       "client.mchelio.MixinMCH_HudItem",
                       "client.mchelio.MixinMCH_RenderAircraft",
                }},
                {ShaderFixerConfig.FixRivalRebelsShaders, new String[] {
                    "client.rivalrebels.client.entity.MixinRenderLaserBurst",
                    "client.rivalrebels.client.entity.MixinRenderLaserLink",
                    "client.rivalrebels.client.entity.MixinRenderLightningBolt2",
                    "client.rivalrebels.client.entity.MixinRenderLightningLink",
                    "client.rivalrebels.client.entity.MixinRenderPlasmoid",
                    "client.rivalrebels.client.model.MixinModelAstroBlasterBody",
                    "client.rivalrebels.client.model.MixinAstroBlasterRenderer",
                    "client.rivalrebels.client.model.MixinModelBlastRing",
                    "client.rivalrebels.client.entity.MixinRenderSphereBlast",
                    "client.rivalrebels.client.entity.MixinRenderTachyonBombBlast",
                    "client.rivalrebels.client.entity.MixinRenderTheoreticalTsarBlast",
                    "client.rivalrebels.client.entity.MixinRenderTsarBlast",
                    "client.rivalrebels.client.entity.MixinRenderAntimatterBombBlast",
                    "client.rivalrebels.client.entity.MixinRenderRhodes",
                    "client.rivalrebels.client.entity.MixinTileEntityForceFieldNodeRenderer",
                    "client.rivalrebels.client.entity.MixinRenderLibrary",
                    "client.rivalrebels.client.entity.MixinRenderBlood",
                    "client.rivalrebels.client.entity.MixinEntityBloodFX",
                    "client.rivalrebels.client.entity.MixinTileEntityPlasmaExplosionRenderer",
                }},
                {ShaderFixerConfig.FixSchematicaShaders, new String[] {
                        "client.Schematica.client.MixinRendererSchematicChunk",
                        "client.Schematica.client.MixinRendererSchematicGlobal",
                        "client.Schematica.client.MixinRenderHelper",
                }},
                {ShaderFixerConfig.FixJourneymapShaders, new String[] {
                        "client.Journeymap.MixinDrawUtil",
                }},
                {ShaderFixerConfig.FixAvaritiaShaders, new String[] {
                   "client.avaritia.client.MixinCosmicItemRenderer",
                   "client.avaritia.client.MixinModelArmorInfinity",
                   "client.avaritia.client.MixinRenderHeavenArrow",
                }},
                {ShaderFixerConfig.FixThaumicConciliumShaders, new String[] {
                   "client.ThaumicConcilium.client.MixinAstralMonitorRenderer",
                   "client.ThaumicConcilium.client.MixinCrimsonOrbEntityRenderer",
                   "client.ThaumicConcilium.client.MixinDissolvedRenderer",
                   "client.ThaumicConcilium.client.MixinDistortionEffectRenderer",
                   "client.ThaumicConcilium.client.MixinHexOfPredictabilityTileRenderer",
                   "client.ThaumicConcilium.client.MixinMaterialPeelerRenderer",
                   "client.ThaumicConcilium.client.MixinQuicksilverElementalRenderer",
                   "client.ThaumicConcilium.client.MixinRiftRenderer",
                }},
                {ShaderFixerConfig.FixOpenComputersShaders, new String[] {
                    "client.oc.client.MixinScreenRenderer",
                    "client.oc.client.MixinHologramRenderer",
                    "client.oc.client.MixinRobotRenderer",
                    "client.oc.client.MixinRenderState",
                }},
                {ShaderFixerConfig.FixElnShaders, ShaderFixerConfig.ElnLightMixins
                        ? new String[] {
                        "client.eln.client.MixinDataLogs",
                        "client.eln.client.MixinUtilsClient",
                        "client.eln.client.MixinLampSocketStandardObjRender",
                        "client.eln.client.MixinLampSocketSuspendedObjRender",
                        "client.eln.client.MixinNixieTubeDescriptor"
                    }
                        : new String[] {
                        "client.eln.client.MixinDataLogs",
                        "client.eln.client.MixinUtilsClient",
                        "client.eln.client.MixinNixieTubeDescriptor"
                }},
                
                {ShaderFixerConfig.FixHbmShaders,
                        Stream.concat(
                        Stream.concat(
                        Arrays.stream(new String[] {
                            "client.hbm.client.MixinParticleAmatFlash",     //Antimatter explosion
                            "client.hbm.client.MixinParticleDebugLine",     //Drone path lines
                            "client.hbm.client.MixinParticleRadiationFog",  //GL11.GL_LIGHTING
                            "client.hbm.client.MixinParticleSpark",         //"Tauon" turret, arc welder
                            "client.hbm.client.MixinParticleSpentCasing",   //Smoke on bullets/shells
                            specjork ? "client.hbm.client.MixinBeamPronter" : "client.hbm.client.MixinBeamPronterORIG", //FEL laser, ICF laser
                            "client.hbm.client.MixinItemRenderDetonatorLaser", //LED on top, sine wave on the side
                            "client.hbm.client.MixinItemRendererMeteorSword", //Lighting glitch in 3rd person view
                            "client.hbm.client.MixinItemRenderLibrary", //Radiation-Powered Engine lights render in hand
                            "client.hbm.client.MixinModelArmorEnvsuit", //Armor helmet lamps
                            "client.hbm.client.MixinRenderBAT9000", //Liquid inside
                            "client.hbm.client.MixinRenderBeam", //Immolator/ HPP LaserJet projectile
                            "client.hbm.client.MixinRenderBeam2", //Power Fist (Zapper) projectile
                            "client.hbm.client.MixinRenderBeam3", //Power Fist (Extracting Mining Laser) projectile
                            "client.hbm.client.MixinRenderBeam4", //Spark Plug projectile
                            "client.hbm.client.MixinRenderBeam5", //B92 projectile
                            "client.hbm.client.MixinRenderBeam6", //B92 projectile
                            "client.hbm.client.MixinRenderBlackHole", //Jet from the center along the poles
                            "client.hbm.client.MixinRenderBobble", //Pu-238 Bobble
                            "client.hbm.client.MixinRenderCloudRainbow", //Flickering field, during a DFC explosion, from a B93 projectile
                            "client.hbm.client.MixinRenderCore", //Sphere of the block itself, additional spheres when it works
                            "client.hbm.client.MixinRenderCraneConsole", //Buttons on the console
                            "client.hbm.client.MixinRenderDeathBlast", //From the orbital death ray
                            "client.hbm.client.MixinRenderDemonLamp", //Disk of blue light, brighter with shaders
                            "client.hbm.client.MixinRenderFlare", //Flare grenade, brighter with shaders
                            "client.hbm.client.MixinRenderFOEQ", //Fire behind the falling shuttle
                            "client.hbm.client.MixinRenderLantern", //Light in the lamp
                            "client.hbm.client.MixinRenderLanternINNER", //Light in the lamp
                            "client.hbm.client.MixinRenderLanternBehemoth", //Light in the lamp
                            "client.hbm.client.MixinRenderMachineForceField", //Green lines forming a sphere
                            "client.hbm.client.MixinRenderMixer", //Liquid inside
                            "client.hbm.client.MixinRenderOverhead", //Red square near entities
                            "client.hbm.client.MixinRenderPumpjack", //Pumpjack bridle
                            "client.hbm.client.MixinRenderRadarScreen", //Green bar going from top to bottom
                            "client.hbm.client.MixinRenderRadGen", //2 lights on top of engine
                            "client.hbm.client.MixinRenderRBMKConsole", //Buttons on the console
                            "client.hbm.client.MixinRenderRBMKLid", //Cherenkov radiation
                            "client.hbm.client.MixinRenderSiegeCraft", //4 lights on top
                            "client.hbm.client.MixinRenderSiegeLaser", //UFO projectile
                            "client.hbm.client.MixinRenderSmallReactor", //Cherenkov's radiation, brighter with shaders
                            "client.hbm.client.MixinRenderSolarBoiler", //Rays of light
                            "client.hbm.client.MixinRenderSparks", //Lightning in a breeding reactor/DFC
                            "client.hbm.client.MixinRenderSpear", //Rays of light from the digamma spear
                            "client.hbm.client.MixinRenderOrbus", //Liquid inside
                            "client.hbm.client.MixinRenderBullet", //!GLASS/TAU
                            "client.hbm.client.MixinRenderRainbow", //!ZOMG
                            "client.hbm.client.sedna.MixinLegoClient", //Bullet render
                            "client.hbm.client.sedna.MixinModEventHandlerRenderer", //Redirect to out system
                            "client.hbm.client.sedna.MixinItemRenderWeaponBase", //Smoke, flash brightness
                            "client.hbm.client.sedna.guns.MixinItemRenderAtlas", //Patch texture after smoke
                            "client.hbm.client.sedna.guns.MixinItemRenderDANI", //Patch dual texture
                            "client.hbm.client.sedna.guns.MixinItemRenderHenry", //Patch texture after smoke
                            "client.hbm.client.sedna.guns.MixinItemRenderMaresleg", //Patch texture after smoke
                            "client.hbm.client.sedna.guns.MixinItemRenderMareslegAkimbo", //Patch texture after smoke
                            "client.hbm.client.MixinRenderChemical", //Antimatter thing
                            "client.hbm.client.MixinRenderSolidifier", //Liquid inside
                            "client.hbm.client.MixinRenderLiquefactor", //Liquid inside
                            "client.hbm.client.MixinRenderRefueler", //Liquid & glClipPlane
                            "client.hbm.client.MixinModelNo9", //Lamp
                            "client.hbm.client.MixinRenderCharger", //Lamp
                            "client.hbm.client.MixinRenderFurnaceSteel", //Heat thing
                            "client.hbm.client.MixinModelArmorWingsPheo", //idk (I know)
                            "client.hbm.client.MixinRenderTorex", //MUSHROOM
                            "client.hbm.client.MixinDiamondPronter", //NFPA 704 (on barrels, tanks)
                        }),
                            specjork ? Arrays.stream(new String[] {
                                "client.hbm.client.MixinSkyProviderCelestial",  //Sky
                                "client.hbm.client.MixinSkyProviderLaytheSunset",   //"Fix" for angelica
                            }) : Stream.empty()
                        ),
                            ShaderFixerConfig.HbmExtendedHazardDescriptions ? Arrays.stream(new String[] {
                                "client.hbm.client.descr.MixinHazardTypeAsbestos",
                                "client.hbm.client.descr.MixinHazardTypeBlinding",
                                "client.hbm.client.descr.MixinHazardTypeCoal",
                                "client.hbm.client.descr.MixinHazardTypeExplosive",
                                "client.hbm.client.descr.MixinHazardTypeHot",
                                "client.hbm.client.descr.MixinHazardTypeHydroactive",
                            }) : Stream.empty()
                        ).toArray(String[]::new)
                },
            {ShaderFixerConfig.FixDSShaders, new String[] {
                "client.DynamicSurroundings.client.MixinAuroraRenderer",
            }},
            {ShaderFixerConfig.FixLMMEShaders, new String[] {
                "client.lmme.MixinGuiInventory",
            }},
            {ShaderFixerConfig.FixHEEhaders, new String[] {
                "client.HEE.MixinRenderBossDragon",
                "client.HEE.MixinRenderWeatherLightningBoltPurple",
                "client.HEE.MixinModClientProxy",
            }},
            {ShaderFixerConfig.PatchHBMAngelica, new String[] {
                "client.angelica.MixinHandRenderer",
            }},
            {ShaderFixerConfig.FixCNPCShaders, new String[] {
                "client.cnpc.MixinRenderChatMessages",
                "client.cnpc.MixinRenderNPCInterface",
            }},
                
            //ADD THERE ^
                
        };
    }
    
    private void handleFix(Object mixinData, List<String> mixins) {
        if (mixinData instanceof String) {
            addMixin((String) mixinData, mixins);
        } else if (mixinData instanceof String[]) {
            for (String mixin : (String[]) mixinData) {
                addMixin(mixin, mixins);
            }
        }
    }
    private void addMixin(String mixinClass, List<String> mixins) {
        String[] parts = mixinClass.split("\\.");
        
        for (String part : parts) {
            if (part.startsWith("Mixin")) {
                ShadersFixer.logger.info("Integrating {}...", part);
                break;
            }
        }
        
        mixins.add(mixinClass);
    }
    private boolean checkNEIVersion() {
        if (Loader.isModLoaded("NotEnoughItems")) {
            return Loader.instance().getIndexedModList().get("NotEnoughItems").getVersion().equals("1.0.5.120");
        }
        return false;
    }
    private boolean checkCCCVersion() {
        if (Loader.isModLoaded("CodeChickenCore")) {
            return Loader.instance().getIndexedModList().get("CodeChickenCore").getVersion().equals("1.0.7.48");
        }
        return false;
    }
    private boolean checkAvaritiaVersion() {
        if (Loader.isModLoaded("Avaritia")) {
            return Loader.instance().getIndexedModList().get("Avaritia").getVersion().equals("1.13");
        }
        return false;
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
