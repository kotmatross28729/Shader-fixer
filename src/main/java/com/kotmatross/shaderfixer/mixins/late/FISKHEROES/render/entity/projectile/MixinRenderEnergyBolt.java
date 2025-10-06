package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity.projectile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.projectile.RenderEnergyBolt;
import com.fiskmods.heroes.common.entity.projectile.EntityEnergyBolt;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RenderEnergyBolt.class, priority = 999)
public class MixinRenderEnergyBolt {

    // ALPHA FIX???

    @Inject(
        method = "renderBolt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void renderBolt(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "renderBolt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderBolt2(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(
        method = "renderBolt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void renderBolt_PF(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(Utils.ProgramUtils.GLGetCurrentProgram());
        Utils.ProgramUtils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderBolt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void renderBolt_PFE(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        Utils.ProgramUtils.GLUseProgram(shader_fixer$program.get());
    }
}
