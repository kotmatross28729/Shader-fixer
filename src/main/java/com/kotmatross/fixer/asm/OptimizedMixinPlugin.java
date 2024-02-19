package com.kotmatross.fixer.asm;

import com.kotmatross.fixer.Fixer;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OptimizedMixinPlugin implements IMixinConfigPlugin {

    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();
        Fixer.logger.info("Trying to load fixer mixins ...");

            if (side == MixinEnvironment.Side.CLIENT) {
                    try {
                        mixins.add("client.FiskHeroes.MixinBulletProjectileRenderer");
                        Fixer.logger.info("client.FiskHeroes.MixinBulletProjectileRenderer loaded ");

                        mixins.add("client.FiskHeroes.MixinProjectileRenderHandler");
                        Fixer.logger.info("client.FiskHeroes.MixinProjectileRenderHandler loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderEnergyBolt");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderEnergyBolt loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderGrappleRope");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderGrappleRope loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderGrapplingHook");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderGrapplingHook loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderSonicWave");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderSonicWave loaded ");

                        mixins.add("client.FiskHeroes.MixinSHRenderHooks");
                        Fixer.logger.info("client.FiskHeroes.MixinSHRenderHooks loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderSpellWhip");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderSpellWhip loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderEarthCrack");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderEarthCrack loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderGravityWave");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderGravityWave loaded ");

                        mixins.add("client.FiskHeroes.MixinEffectTentacles");
                        Fixer.logger.info("client.FiskHeroes.MixinEffectTentacles loaded ");

                        mixins.add("client.FiskHeroes.MixinEntitySHSpellWaveFX");
                        Fixer.logger.info("client.FiskHeroes.MixinEntitySHSpellWaveFX loaded ");

                        mixins.add("client.FiskHeroes.MixinShapeFormatCircles");
                        Fixer.logger.info("client.FiskHeroes.MixinShapeFormatCircles loaded ");

                        mixins.add("client.FiskHeroes.MixinShapeFormatLines");
                        Fixer.logger.info("client.FiskHeroes.MixinShapeFormatLines loaded ");

                        mixins.add("client.FiskHeroes.MixinShapeFormatWireframe");
                        Fixer.logger.info("client.FiskHeroes.MixinShapeFormatWireframe loaded ");

                        mixins.add("client.FiskHeroes.MixinBeamRendererLine");
                        Fixer.logger.info("client.FiskHeroes.MixinBeamRendererLine loaded ");

                        mixins.add("client.FiskHeroes.MixinBeamRendererLightning");
                        Fixer.logger.info("client.FiskHeroes.MixinBeamRendererLightning loaded ");

                        mixins.add("client.FiskHeroes.MixinRenderSuitFabricator");
                        Fixer.logger.info("client.FiskHeroes.MixinRenderSuitFabricator loaded ");
                    } catch (Exception e) {
                        Fixer.logger.error("Error loading mixins: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

}
