package com.kotmatross.shadersfixer;

import net.irisshaders.iris.api.v0.IrisApi;

public class AngelicaUtils {

    public static boolean isShaderEnabled() {
        if (ShadersFixer.IS_ANGELICA_PRESENT) {
            return IrisApi.getInstance()
                .isShaderPackInUse();
        }
        return false;
    }
}
