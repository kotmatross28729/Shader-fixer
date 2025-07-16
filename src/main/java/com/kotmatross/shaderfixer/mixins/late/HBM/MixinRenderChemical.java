package com.kotmatross.shaderfixer.mixins.late.HBM;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.projectile.EntityChemical;
import com.hbm.render.entity.projectile.RenderChemical;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderChemical.class, priority = 999)
public class MixinRenderChemical {

    @Inject(
        method = "renderAmatBeam",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderAmatBeam(EntityChemical chem, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(
        method = "renderAmatBeam",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderAmatBeamPR(EntityChemical chem, float interp, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderAmatBeam",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderAmatBeamPRE(EntityChemical chem, float interp, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }

}
