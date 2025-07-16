package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderFOEQ;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderFOEQ.class, priority = 999)
public class MixinRenderFOEQ {

    @Inject(
        method = "func_76986_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2),
        remap = false)
    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
