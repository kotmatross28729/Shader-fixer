package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.pack.json.beam;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.pack.json.beam.BeamRendererLaser;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = BeamRendererLaser.class, priority = 999)
public class MixinBeamRendererLaser {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void render_PF(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram(); // causes problems with angelica
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void render_PFE(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }
}
