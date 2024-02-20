package com.kotmatross.shadersfixer.asm;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
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

        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadMixinConfig(new File(Launch.minecraftHome, configFolder + "mixins.cfg"));

            if (side == MixinEnvironment.Side.CLIENT) {
                ShadersFixer.logger.info("Integrating Shader-fixer client mixins...");
                    try {
                        if(ShaderFixerConfig.FixFisksuperheroesShaders) {
                            //FISK HEROES MIXINS START
                            ShadersFixer.logger.info("Trying to integrate fiskheroes mixins");
                            mixins.add("client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLightning");
                            mixins.add("client.FiskHeroes.client.pack.json.beam.MixinBeamRendererLine");
                            mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatCircles");
                            mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatLines");
                            mixins.add("client.FiskHeroes.client.pack.json.shape.MixinShapeFormatWireframe");
                            mixins.add("client.FiskHeroes.client.particle.MixinEntitySHSpellWaveFX");
                            mixins.add("client.FiskHeroes.client.render.effect.MixinEffectTentacles");
                            mixins.add("client.FiskHeroes.client.render.entity.effect.MixinRenderEarthCrack");
                            mixins.add("client.FiskHeroes.client.render.entity.effect.MixinRenderGravityWave");
                            mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderEnergyBolt");
                            mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderGrapplingHook");
                            mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderSonicWave");
                            mixins.add("client.FiskHeroes.client.render.entity.projectile.MixinRenderSpellWhip");
                            mixins.add("client.FiskHeroes.client.render.entity.MixinRenderGrappleRope");
                            mixins.add("client.FiskHeroes.client.render.projectile.MixinBulletProjectileRenderer");
                            mixins.add("client.FiskHeroes.client.render.projectile.MixinProjectileRenderHandler");
                            mixins.add("client.FiskHeroes.client.render.tile.MixinRenderSuitFabricator");
                            mixins.add("client.FiskHeroes.client.MixinSHRenderHooks");
                            //FISK HEROES MIXINS END
                        }
                    } catch (Exception e) {
                        ShadersFixer.logger.error("Error loading mixins: " + e.getMessage());
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
