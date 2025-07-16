package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.particle.ParticleRadiationFog;

@Mixin(value = ParticleRadiationFog.class, priority = 999)
public class MixinParticleRadiationFog {

    // Me when bob do 2 lightings:
    @Inject(
        method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2),
        cancellable = true,
        remap = false)
    private void renderLighting(Tessellator tess, float p_70539_2_, float p_70539_3_, float p_70539_4_,
        float p_70539_5_, float p_70539_6_, float p_70539_7_, CallbackInfo ci) {
        ci.cancel();
    }
}
