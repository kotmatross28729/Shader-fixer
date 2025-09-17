package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.pack.json.beam;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.beam.BeamRendererLightning.class, priority = 999)
public abstract class MixinBeamRendererLightning implements com.fiskmods.heroes.client.pack.json.beam.IBeamRenderer {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    // @Inject(
    // method = "render",
    // at = @At(
    // value = "INVOKE",
    // target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
    // shift = At.Shift.BEFORE))
    // public void render_PF(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
    // Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
    // boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci,
    // @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
    // shader_fixer$program.set(Utils.GLGetCurrentProgram());
    // Utils.GLUseDefaultProgram();
    // }
    //
    // @Inject(
    // method = "render",
    // at = @At(
    // value = "INVOKE",
    // target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
    // shift = At.Shift.AFTER))
    // public void render_PFE(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
    // Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
    // boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci,
    // @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
    // Utils.GLUseProgram(shader_fixer$program.get());
    // }
}
