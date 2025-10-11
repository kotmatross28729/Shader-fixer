package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.projectile.EntityChemical;
import com.hbm.render.entity.projectile.RenderChemical;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderChemical.class, priority = 999)
public class MixinRenderChemical {

    @Inject(
        method = "renderAmatBeam",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    private void renderAmatBeam(EntityChemical chem, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "renderAmatBeam",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    private void renderAmatBeam2(EntityChemical chem, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @ModifyArg(
        method = "renderAmatBeam",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 2F;
    }

}
