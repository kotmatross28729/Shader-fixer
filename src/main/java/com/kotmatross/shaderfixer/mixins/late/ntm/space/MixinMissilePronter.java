package com.kotmatross.shaderfixer.mixins.late.ntm.space;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.MissilePronter;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;

@SPEKJORK
@Mixin(value = MissilePronter.class, priority = 999)
public class MixinMissilePronter {

    @Inject(
        method = "prontRocket*",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 0),
        remap = false)
    private static void prontRocket(CallbackInfo ci) {
        GL11.glPushMatrix();
        // Actually 0.99F also works, but we take it with a reserve (0.99F may flicker at some angles)
        GL11.glScalef(0.97F, 1.0F, 0.97F);
    }

    @Inject(
        method = "prontRocket*",
        at = @At(
            value = "INVOKE",
            target = "org/lwjgl/opengl/GL11.glDisable (I)V",
            ordinal = 0,
            shift = At.Shift.AFTER),
        remap = false)
    private static void prontRocket2(CallbackInfo ci) {
        GL11.glPopMatrix();
    }

}
