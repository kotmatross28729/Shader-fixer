package com.kotmatross.shadersfixer.asm;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.Name("ShadersFixerEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class ShadersFixerEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.shadersfixer.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        ShadersFixer.logger.info("Starting Shaderfixer engine...");
        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadEarlyMixinConfig(new File(Launch.minecraftHome, configFolder + "mixinsEarly.cfg"));
        boolean client = FMLLaunchHandler.side().isClient();

        List<String> mixins = new ArrayList<>();

        if(client) {
            if(ShaderFixerConfig.FixMinecraftHitboxesRender) {
                ShadersFixer.logger.info("Integrating drawOutlinedBoundingBox mixin...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderGlobal");
            }
            if(ShaderFixerConfig.FixMinecraftFishinglineRender) {
                ShadersFixer.logger.info("Integrating MixinRenderFish...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderFish");
            }
            if(ShaderFixerConfig.FixMinecraftLeashRender) {
                ShadersFixer.logger.info("Integrating MixinRenderLiving...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderLiving");
            }
            if(ShaderFixerConfig.EnableXMixinRenderLiving) {
                ShadersFixer.logger.info("Integrating XMixinRenderLiving... (for Damage Indicators fix)");
                mixins.add("client.minecraft.client.renderer.entity.XMixinRenderLiving");
            }
            if(ShaderFixerConfig.FixMinecraftEnderdragonDeathEffectsRender) {
                ShadersFixer.logger.info("Integrating MixinRenderDragon...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderDragon");
            }
            if(ShaderFixerConfig.FixMinecraftLightningBoltRender) {
                ShadersFixer.logger.info("Integrating MixinRenderLightningBolt...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderLightningBolt");
            }
            if(ShaderFixerConfig.FixMinecraftNameTagsRender) {
                ShadersFixer.logger.info("Integrating MixinRender...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRender");
                ShadersFixer.logger.info("Integrating MixinRendererLivingEntity...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRendererLivingEntity");
            }
            //TODO test mixin
            mixins.add("client.minecraft.client.renderer.entity.MixinRenderManager");
        }

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
