package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraft.client.renderer.Tessellator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.tileentity.RenderPylonBase;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = RenderPylonBase.class, priority = 999)
public class MixinRenderPylonBase {

    @Inject(method = "drawLineSegment", at = @At(value = "HEAD"), remap = false)
    private void drawLineSegment(CallbackInfo ci, @Local(argsOnly = true) Tessellator tessellator) {
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
    }

}
