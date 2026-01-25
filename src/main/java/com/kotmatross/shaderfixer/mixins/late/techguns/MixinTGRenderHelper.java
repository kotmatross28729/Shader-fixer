package com.kotmatross.shaderfixer.mixins.late.techguns;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.kotmatross.shaderfixer.utils.ShaderUtils;

import techguns.client.renderer.TGRenderHelper;

@Mixin(value = TGRenderHelper.class, priority = 999)
public class MixinTGRenderHelper {

    /**
     * @author kotmatross
     * @reason Fix lighting leak
     */
    @Overwrite(remap = false)
    public static void enableFXLighting() {
        ShaderUtils.enableFullBrightness();
    }

    /**
     * @author kotmatross
     * @reason Fix lighting leak
     */
    @Overwrite(remap = false)
    public static void disableFXLighting() {
        ShaderUtils.disableFullBrightness();
    }

}
