package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.hbm.render.tileentity.RenderCore;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

@Mixin(value = RenderCore.class, priority = 999)
public class MixinRenderCore {

    @WrapMethod(method = "renderTileEntityAt")
    private void dontCastShadow(TileEntity tileEntity, double x, double y, double z, float f,
        Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(tileEntity, x, y, z, f);
        }
    }

    @ModifyArg(
        method = "renderFlare",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F(FFFF)V"),
        index = 3)
    private float alphaFix(float alpha) {
        return alpha == 0 ? 0.01F : alpha * 2F;
    }

}
