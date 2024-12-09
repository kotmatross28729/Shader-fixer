package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.particle.ParticleSpentCasing;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ParticleSpentCasing.class, priority = 999)
public class MixinParticleSpentCasing {
    @Inject(method = "func_70539_a",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_70539_a(Tessellator tessellator, float interp, float x, float y, float z, float tx, float tz, CallbackInfo ci) {
        Utils.Fix();
    }
}
