package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.shape.ShapeFormatLines.class, priority = 999)
public abstract class MixinShapeFormatLines implements com.fiskmods.heroes.client.pack.json.shape.IShapeFormat {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void render(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void render2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

}
