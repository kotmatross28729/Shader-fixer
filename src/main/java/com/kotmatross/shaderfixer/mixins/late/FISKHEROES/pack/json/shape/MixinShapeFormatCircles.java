package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.pack.json.shape;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.shape.ShapeFormatCircles.class, priority = 999)
public abstract class MixinShapeFormatCircles implements com.fiskmods.heroes.client.pack.json.shape.IShapeFormat {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void render(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
        float ticks, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void render2(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
        float ticks, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
