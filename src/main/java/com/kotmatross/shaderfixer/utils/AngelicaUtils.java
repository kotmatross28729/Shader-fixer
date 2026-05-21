package com.kotmatross.shaderfixer.utils;

import com.kotmatross.shaderfixer.mixins.late.angelica.AccessorDeferredWorldRenderingPipeline;
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
    
    protected static int getShadowMapResolution() {
        if (!isShaderEnabled()) {
            return 0;
        }
        return Iris.getPipelineManager().getPipeline()
                .map(pipe -> ((AccessorDeferredWorldRenderingPipeline) pipe)
                        .getShadowMapResolution())
                .orElse(0);
    }

}
