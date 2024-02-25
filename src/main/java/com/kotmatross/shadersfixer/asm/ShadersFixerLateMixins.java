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
        List<String> mixins = new ArrayList<>();

            if (side == MixinEnvironment.Side.CLIENT) {
                ShadersFixer.logger.info("Integrating Shaderfixer late client mixins...");

                if (ShaderFixerConfig.FixFisksuperheroesShaders) {
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
                }
                if (ShaderFixerConfig.FixNEIShaders) {
                    ShadersFixer.logger.info("Trying to integrate NotEnoughItems mixins");
                    mixins.add("client.NotEnoughItems.client.MixinWorldOverlayRenderer");
                }
            }

        return mixins;
    }
}
