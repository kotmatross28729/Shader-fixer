package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.entity.projectile.RenderBeam4;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderBeam4.class, priority = 999)
public class MixinRenderBeam4 {
    @Inject(method = "func_76986_a",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 3)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void func_76986_a(Entity rocket, double x, double y, double z, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
