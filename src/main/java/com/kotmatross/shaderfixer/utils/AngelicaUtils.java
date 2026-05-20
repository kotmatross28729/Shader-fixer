package com.kotmatross.shaderfixer.utils;

import net.coderbot.iris.Iris;
import net.irisshaders.iris.api.v0.IrisApi;

/// Use {@link com.kotmatross.shaderfixer.utils.AngelicaUtils_WRAPPER}
class AngelicaUtils {

    protected static boolean isShaderEnabled() {
        return IrisApi.getInstance()
            .isShaderPackInUse();
    }

    protected static boolean isComplementary() {
        if (!isShaderEnabled()) {
            return false;
        }
        String name = Iris.getIrisConfig()
            .getShaderPackName()
            .orElse(null);
        return name != null && name.contains("Complementary");
    }

    protected static boolean isShadowPass() {
        return IrisApi.getInstance()
            .isRenderingShadowPass();
    }

}
