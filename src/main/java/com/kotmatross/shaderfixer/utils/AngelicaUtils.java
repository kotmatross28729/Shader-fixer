package com.kotmatross.shaderfixer.utils;

import net.coderbot.iris.Iris;
import net.irisshaders.iris.api.v0.IrisApi;

import com.kotmatross.shaderfixer.ShaderFixer;

public class AngelicaUtils {

    public static boolean isShaderEnabled() {
        if (ShaderFixer.IS_ANGELICA_PRESENT) {
            return IrisApi.getInstance()
                .isShaderPackInUse();
        }
        return false;
    }

    public static boolean isComplementary() {
        if (ShaderFixer.IS_ANGELICA_PRESENT) {
            return isShaderEnabled() && Iris.getIrisConfig()
                .getShaderPackName()
                .map(name -> name.contains("Complementary"))
                .orElse(false);
        }
        return false;
    }

    public static boolean isShadowPass() {
        if (ShaderFixer.IS_ANGELICA_PRESENT) {
            return IrisApi.getInstance()
                .isRenderingShadowPass();
        }
        return false;
    }

}
