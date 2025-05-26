package com.kotmatross.shadersfixer.asm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

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
        boolean client = FMLLaunchHandler.side()
            .isClient();

        List<String> mixins = new ArrayList<>();

        if (client) {

            // VANILLA

            if (ShaderFixerConfig.FixMinecraftHitboxesRender) {
                ShadersFixer.logger.info("Integrating drawOutlinedBoundingBox mixin...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderGlobal");
            }
            if (ShaderFixerConfig.FixMinecraftFishinglineRender) {
                ShadersFixer.logger.info("Integrating MixinRenderFish...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderFish");
            }
            if (ShaderFixerConfig.FixMinecraftLeashRender) {
                ShadersFixer.logger.info("Integrating MixinRenderLiving...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderLiving");
            }
            if (ShaderFixerConfig.EnableXMixinRenderLiving) {
                ShadersFixer.logger.info("Integrating XMixinRenderLiving... (for Damage Indicators fix)");
                mixins.add("client.minecraft.client.renderer.entity.XMixinRenderLiving");
            }
            if (ShaderFixerConfig.FixMinecraftEnderdragonDeathEffectsRender) {
                ShadersFixer.logger.info("Integrating MixinRenderDragon...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderDragon");
            }
            if (ShaderFixerConfig.FixMinecraftLightningBoltRender) {
                ShadersFixer.logger.info("Integrating MixinRenderLightningBolt...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderLightningBolt");
            }
            if (ShaderFixerConfig.FixMinecraftNameTagsRender) {
                ShadersFixer.logger.info("Integrating MixinRender...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRender");
                ShadersFixer.logger.info("Integrating MixinRendererLivingEntity...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRendererLivingEntity");
            }
            if (ShaderFixerConfig.FixMinecraftEffectGUIBlending) {
                ShadersFixer.logger.info("Integrating MixinInventoryEffectRenderer...");
                mixins.add("client.minecraft.client.renderer.entity.MixinInventoryEffectRenderer");
            }
            if (ShaderFixerConfig.FixHbmGunsRender) {
                ShadersFixer.logger.info("Integrating MixinEntityRenderer...");
                mixins.add("client.minecraft.client.renderer.entity.sedna.MixinEntityRenderer");
                ShadersFixer.logger.info("Integrating MixinItemRenderer...");
                mixins.add("client.minecraft.client.renderer.entity.sedna.MixinItemRenderer");
                ShadersFixer.logger.info("Integrating MixinForgeHooksClient...");
                mixins.add("client.minecraft.client.renderer.entity.sedna.MixinForgeHooksClient");
            }
            if (ShaderFixerConfig.UnlockMainMenuFPS) {
                ShadersFixer.logger.info("Integrating MixinMinecraft...");
                mixins.add("client.minecraft.client.renderer.entity.MixinMinecraft");
            }
            if (ShaderFixerConfig.FixRidingHand || ShaderFixerConfig.DisableRidingHandRotation) {
                ShadersFixer.logger.info("Integrating MixinRenderPlayer...");
                mixins.add("client.minecraft.client.renderer.entity.MixinRenderPlayer");
            }

            if (ShaderFixerConfig.ModernRidingLegsPos) {
                ShadersFixer.logger.info("Integrating MixinModelBiped2...");
                mixins.add("client.minecraft.client.renderer.entity.MixinModelBiped2");
            }

            if (ShaderFixerConfig.FixHbmGunArmorRender) {
                ShadersFixer.logger.info("Integrating MixinModelBiped...");
                mixins.add("client.minecraft.client.renderer.entity.armorfix.MixinModelBiped");
                ShadersFixer.logger.info("Integrating MixinRenderBiped...");
                mixins.add("client.minecraft.client.renderer.entity.armorfix.MixinRenderBiped");
            }

            // SPECIAL MIXINS

            // if (loadedCoreMods.contains("com.gtnewhorizons.angelica.loading.AngelicaTweaker")) {
            // }

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
