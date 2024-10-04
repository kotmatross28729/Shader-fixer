package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.particle.ParticleRadiationFog;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ParticleRadiationFog.class, priority = 999)
public class MixinParticleRadiationFog {
    @Unique
    public boolean shaders_fixer$lighting;


    @Inject(method = "func_70539_a", at =  @At(value = "HEAD"), remap = false)
    public void shaders_fixer$lightingGET(Tessellator tess, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_, CallbackInfo ci) {
        shaders_fixer$lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
    }

    @Redirect(method = "func_70539_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        require = 1, remap = false)
    public void enableLighting(int cap) {
        if (cap == GL11.GL_LIGHTING && shaders_fixer$lighting)
            GL11.glEnable(cap);
    }
}
