package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.pack.json.beam;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.pack.json.beam.BeamRendererLaser;
import com.fiskmods.heroes.client.pack.json.beam.Bloom;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

@Mixin(value = BeamRendererLaser.class, priority = 999)
public class MixinBeamRendererLaser {

    /**
     * Soooo, it turns out Complementary has the opposite problem with alpha than BSL:
     * If alpha < ~0.1, then in BSL it's displayed with an alpha of 1.0,
     * but in Complementary, somehow, if alpha < ~0.1, then it's displayed with an alpha of... 0.0
     */

    @Shadow
    private Bloom bloom;

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/Vec3;distanceTo(Lnet/minecraft/util/Vec3;)D",
            shift = At.Shift.AFTER))
    public void fixAlpha(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst,
        Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale,
        boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci,
        @Local(ordinal = 11) LocalFloatRef alpha) {
        if (AngelicaUtils.isShaderEnabled()) {
            if (this.bloom != null) {
                // Looks like something dense, high alpha
                if (1.0F / this.bloom.getStrength() > 1.5F) alpha.set(0.4F);
                // Looks like something NOT dense, low alpha
                if (1.0F / this.bloom.getStrength() < 1.5F) alpha.set(0.2F);
            }
        }
    }

}
