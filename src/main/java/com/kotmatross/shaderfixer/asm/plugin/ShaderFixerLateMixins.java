package com.kotmatross.shaderfixer.asm.plugin;

import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.kotmatross.shaderfixer.asm.LateMixins;

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
