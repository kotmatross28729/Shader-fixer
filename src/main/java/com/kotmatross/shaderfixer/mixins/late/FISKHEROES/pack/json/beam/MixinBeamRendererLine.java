package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.pack.json.beam;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.beam.BeamRendererLine.class, priority = 999)
public abstract class MixinBeamRendererLine implements com.fiskmods.heroes.client.pack.json.beam.IBeamRenderer {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    public void render(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    public void render2(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 1,
            shift = At.Shift.BEFORE))
    public void render3(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 1,
            shift = At.Shift.AFTER))
    public void render4(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
