package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.particle;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.particle.EntitySHSpellWaveFX;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = EntitySHSpellWaveFX.class, priority = 999)
public class MixinEntitySHSpellWaveFX {

    @Inject(
        method = "func_70539_a",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0))
    public void func_70539_a(Tessellator tessellator, float partialTicks, float f, float f1, float f2, float f3,
        float f4, CallbackInfo ci) {
        Utils.fix();
    }

    @ModifyArg(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha, @Local(ordinal = 10) LocalFloatRef opacity) {
        return alpha == 0 ? opacity.get() : alpha * 3;
    }

}
