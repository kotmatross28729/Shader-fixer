package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.particle;

import com.fiskmods.heroes.client.particle.EntitySHSpellWaveFX;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntitySHSpellWaveFX.class, priority = 999)
public class MixinEntitySHSpellWaveFX {
    @Inject(method = "func_70539_a",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 1)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_70539_a(Tessellator tessellator, float partialTicks, float f, float f1, float f2, float f3, float f4, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
