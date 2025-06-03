package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ilya3point999k.thaumicconcilium.client.render.DistortionEffectRenderer;
import com.ilya3point999k.thaumicconcilium.common.entities.DistortionEffectEntity;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = DistortionEffectRenderer.class, priority = 999)
public class MixinDistortionEffectRenderer {

    @Inject(
        method = "renderEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(DistortionEffectEntity entity, double x, double y, double z, float fq, float pticks,
        CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(false);
        // }
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
    }

    @Inject(
        method = "renderEntityAt",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader(DistortionEffectEntity entity, double x, double y, double z, float fq, float pticks,
        CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(true);
        // }
    }
}
