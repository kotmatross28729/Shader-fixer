package com.kotmatross.shaderfixer.asm.plugin;

import java.io.File;
import java.util.List;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.asm.LateMixins;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

import cpw.mods.fml.common.Loader;

@LateMixin
public class ShaderFixerLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.shaderfixer.late.json";
    }

    public static boolean OLD_NEI_LOADED = false;

    @Override
    public List<String> getMixins(Set<String> loadedMods) {

        String configFolder = "config" + File.separator + Tags.MODID + File.separator;

        ShaderFixerConfig
            .loadLateMixinConfigGeneral(new File(Launch.minecraftHome, configFolder + "mixinsLate_GENERAL.cfg"));

        ShaderFixerConfig
            .loadLateMixinConfigTweaks(new File(Launch.minecraftHome, configFolder + "mixinsLate_TWEAKS.cfg"));

        OLD_NEI_LOADED = checkNEIVersion();

        return IMixins.getLateMixins(LateMixins.class, loadedMods);
    }

    private boolean checkNEIVersion() {
        if (Loader.isModLoaded("NotEnoughItems")) {
            return Loader.instance()
                .getIndexedModList()
                .get("NotEnoughItems")
                .getVersion()
                .equals("1.0.5.120");
        }
        return false;
    }

}
