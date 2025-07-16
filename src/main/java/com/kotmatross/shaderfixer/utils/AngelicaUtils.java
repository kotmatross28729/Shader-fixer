package com.kotmatross.shaderfixer.utils;

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
}
