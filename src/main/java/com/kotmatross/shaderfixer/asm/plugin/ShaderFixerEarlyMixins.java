package com.kotmatross.shaderfixer.asm.plugin;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.kotmatross.shaderfixer.ShaderFixer;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.asm.EarlyMixins;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("ShaderFixerEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class ShaderFixerEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.shaderfixer.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        ShaderFixer.logger.info("Starting Shaderfixer engine...");

        String configFolder = "config" + File.separator + Tags.MODID + File.separator;
        ShaderFixerConfig
            .loadEarlyMixinConfigGeneral(new File(Launch.minecraftHome, configFolder + "mixinsEarly_GENERAL.cfg"));
        ShaderFixerConfig
            .loadEarlyMixinConfigTweaks(new File(Launch.minecraftHome, configFolder + "mixinsEarly_TWEAKS.cfg"));

        return IMixins.getEarlyMixins(EarlyMixins.class, loadedCoreMods);
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
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
