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
    
    private Object[][] getFixes() {
        return new Object[][] {
                {ShaderFixerConfig.FixMinecraftHitboxesRender, "MixinRenderGlobal"},
                {ShaderFixerConfig.FixMinecraftFishinglineRender, "MixinRenderFish"},
                {ShaderFixerConfig.FixMinecraftLeashRender, "MixinRenderLiving"},
                {ShaderFixerConfig.EnableXMixinRenderLiving, "XMixinRenderLiving"},
                {ShaderFixerConfig.FixMinecraftEnderdragonDeathEffectsRender, "MixinRenderDragon"},
                {ShaderFixerConfig.FixMinecraftLightningBoltRender, "MixinRenderLightningBolt"},
                {ShaderFixerConfig.FixMinecraftNameTagsRender, new String[] {
                        "MixinRender",
                        "MixinRendererLivingEntity"
                }},
                {ShaderFixerConfig.FixMinecraftEffectGUIBlending, "MixinInventoryEffectRenderer"},
                {ShaderFixerConfig.FixHbmGunsRender, new String[] {
                        "sedna.MixinEntityRenderer",
                        "sedna.MixinItemRenderer",
                        "sedna.MixinForgeHooksClient"
                }},
                {ShaderFixerConfig.UnlockMainMenuFPS, "MixinMinecraft"},
                {ShaderFixerConfig.FixRiddingHand, "MixinRenderPlayer"},
        
                //ADD THERE ^
        };
    }
    
    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        ShadersFixer.logger.info("Starting Shaderfixer engine...");
        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig.loadEarlyMixinConfig(new File(Launch.minecraftHome, configFolder + "mixinsEarly.cfg"));
        boolean client = FMLLaunchHandler.side().isClient();

        List<String> mixins = new ArrayList<>();

        if(client) {
            for (Object[] fix : getFixes()) {
                if ((boolean) fix[0]) {
                    handleFix(fix[1], mixins);
                }
            }
        }

        return mixins;
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
        String fullClass = "client.minecraft.client.renderer.entity." + mixinClass;
        ShadersFixer.logger.info("Integrating {}...", mixinClass);
        mixins.add(fullClass);
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
