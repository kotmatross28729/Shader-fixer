package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.effect.Effect;
import com.fiskmods.heroes.client.render.effect.EffectTentacles;

@Mixin(value = EffectTentacles.class, priority = 999, remap = false)
public abstract class MixinEffectTentacles implements Effect {
    
    @Inject(method = "doRender"
            , at = @At(value = "HEAD"))
    private void doRender(CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_DEPTH_BUFFER_BIT);
    }

    @Inject(method = "doRender"
            , at = @At(value = "TAIL"))
    private void doRender2(CallbackInfo ci) {
        GL11.glPopAttrib();
    }

    @Inject(method = "renderTentacle"
            , at = @At(value = "HEAD"))
    private static void renderTentacle(CallbackInfo ci) {
        if (!GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK)) {
            GL11.glDepthMask(true);
        }
    }
    
}
