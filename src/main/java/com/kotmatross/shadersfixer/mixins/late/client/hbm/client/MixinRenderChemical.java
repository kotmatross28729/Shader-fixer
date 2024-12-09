package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.entity.projectile.EntityChemical;
import com.hbm.render.entity.projectile.RenderChemical;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderChemical.class, priority = 999)
public class MixinRenderChemical {

    @Inject(method = "renderAmatBeam",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderAmatBeam(EntityChemical chem, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    public int shaders_fixer$program;
    @Inject(method = "renderAmatBeam",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderAmatBeamPR(EntityChemical chem, float interp, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "renderAmatBeam",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", shift = At.Shift.AFTER))
    public void renderAmatBeamPRE(EntityChemical chem, float interp, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

}
