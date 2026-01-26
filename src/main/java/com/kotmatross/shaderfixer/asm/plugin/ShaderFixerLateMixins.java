package com.kotmatross.shaderfixer.asm.plugin;

import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.kotmatross.shaderfixer.asm.LateMixins;

@LateMixin
public class ShaderFixerLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.shaderfixer.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(LateMixins.class, loadedMods);
    }

}
