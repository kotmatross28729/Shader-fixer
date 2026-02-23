package com.kotmatross.shaderfixer.mixins.early.minecraft.particleAlphaWorkaround;

import net.minecraft.client.particle.EffectRenderer;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.kotmatross.shaderfixer.utils.AngelicaUtils;

@Mixin(value = EffectRenderer.class, priority = 1009)
public class MixinEffectRenderer {

    // todo: Need to fix it properly, not this hacky hack

    /**
     * @author kotmatross
     * @reason Complementary seems to multiply particle colors by alpha again after glBlendFunc with GL_SRC_ALPHA, which
     *         makes the particles with low alpha appear very dark.
     *         This hack simply changes GL_SRC_ALPHA -> GL_ONE if current shader is Complementary, so the alpha
     *         multiplication only happens once - in the shader
     */
    @ModifyArg(
        method = "renderParticles",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBlendFunc(II)V"),
        index = 0)
    private int workaroundComplementaryParticleAlpha(int sfactor, int dfactor) {
        return AngelicaUtils.isComplementary() ? GL11.GL_ONE : sfactor;
    }

}
