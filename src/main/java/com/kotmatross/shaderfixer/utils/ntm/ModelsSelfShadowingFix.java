package com.kotmatross.shaderfixer.utils.ntm;

public class ModelsSelfShadowingFix {
    public static void init() {
        for (ShadowModel type : ShadowModel.values()) {
            type.init();
        }
    }
}
