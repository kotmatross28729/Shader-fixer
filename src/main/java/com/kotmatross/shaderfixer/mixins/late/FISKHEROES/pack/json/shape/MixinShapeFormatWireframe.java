package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.pack.json.shape;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.shape.ShapeFormatWireframe.class, priority = 999)
public abstract class MixinShapeFormatWireframe implements com.fiskmods.heroes.client.pack.json.shape.IShapeFormat {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void render(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
        float ticks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
