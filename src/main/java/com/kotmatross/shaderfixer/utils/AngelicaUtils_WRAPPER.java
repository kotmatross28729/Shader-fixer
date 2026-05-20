package com.kotmatross.shaderfixer.utils;

import com.kotmatross.shaderfixer.ShaderFixer;

public class AngelicaUtils_WRAPPER {

    public static boolean isShaderEnabled() {
        if (!ShaderFixer.IS_ANGELICA_PRESENT) {
            return false;
        }
        return AngelicaUtils.isShaderEnabled();
    }

    public static boolean isComplementary() {
        if (!ShaderFixer.IS_ANGELICA_PRESENT) {
            return false;
        }
        return AngelicaUtils.isComplementary();
    }

    public static boolean isShadowPass() {
        if (!ShaderFixer.IS_ANGELICA_PRESENT) {
            return false;
        }
        return AngelicaUtils.isShadowPass();
    }

}
