package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.render.entity.effect.RenderCloudRainbow;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderCloudRainbow.class, priority = 999)
public class MixinRenderCloudRainbow {

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render(EntityCloudFleijaRainbow cloud, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
