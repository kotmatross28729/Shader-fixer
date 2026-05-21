package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.factory.LegoClient;
import com.kotmatross.shaderfixer.utils.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = LegoClient.class, priority = 999, remap = false)
public class MixinLegoClient {

    @WrapMethod(method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V")
    private static void dontCastShadow(Tessellator tess, int dark, int light, double length, double widthF,
        double widthB, boolean fullbright, Operation<Void> original) {
        if (!AngelicaUtils_WRAPPER.isShadowPass()) {
            original.call(tess, dark, light, length, widthF, widthB, fullbright);
        }
    }

    @Inject(method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V"
            , at = @At(value = "HEAD"))
    private static void renderBulletStandard(CallbackInfo ci, @Local(argsOnly = true) boolean fullbright) {
        if (fullbright) ShaderUtils.enableFullBrightness();
    }

    @Inject(method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V"
            , at = @At(value = "TAIL"))
    private static void renderBulletStandard2(CallbackInfo ci, @Local(argsOnly = true) boolean fullbright) {
        if (fullbright) ShaderUtils.disableFullBrightness();
    }

    @Inject(method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V"
            , at = @At(value = "INVOKE"
                , target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"
                , remap = true
                , shift = At.Shift.AFTER))
    private static void renderBulletStandard3(CallbackInfo ci, @Local(argsOnly = true) Tessellator tess) {
        if (AngelicaUtils_WRAPPER.isShaderEnabled()) tess.setBrightness(240);
    }

    @Inject(method = "drawLineSegment"
            , at = @At(value = "HEAD"))
    private static void drawLineSegment(CallbackInfo ci, @Local(argsOnly = true) Tessellator tessellator) {
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
    }

}
