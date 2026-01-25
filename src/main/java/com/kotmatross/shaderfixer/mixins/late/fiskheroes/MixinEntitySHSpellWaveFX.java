package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.fiskmods.heroes.client.particle.EntitySHSpellWaveFX;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = EntitySHSpellWaveFX.class, priority = 999)
public class MixinEntitySHSpellWaveFX {

    @ModifyArg(
        method = "renderParticle",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha, @Local(ordinal = 10) LocalFloatRef opacity) {
        return alpha == 0 ? opacity.get() : alpha * 3;
    }

}
