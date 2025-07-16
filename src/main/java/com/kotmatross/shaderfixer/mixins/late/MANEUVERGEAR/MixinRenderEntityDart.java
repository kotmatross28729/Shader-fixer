package com.kotmatross.shaderfixer.mixins.late.MANEUVERGEAR;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.InfinityRaider.maneuvergear.render.RenderEntityDart;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderEntityDart.class, priority = 999)
public class MixinRenderEntityDart {

    @Inject(
        method = "renderWire",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderWire(Tessellator tessellator, double x, double y, double z, double X, double Y, double Z,
        double A, CallbackInfo ci) {
        Utils.Fix();
    }

}
