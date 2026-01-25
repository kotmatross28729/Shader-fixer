package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.hbm.render.tileentity.RenderSolarBoiler;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;

@Mixin(value = RenderSolarBoiler.class, priority = 999)
public class MixinRenderSolarBoiler {

    @ModifyArg(
        method = "renderTileEntityAt",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return AngelicaUtils.isShaderEnabled() ? (alpha == 0.005F ? 0.1F : 0.2F) : alpha;
    }

}
