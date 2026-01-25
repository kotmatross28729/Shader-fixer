package com.kotmatross.shaderfixer.mixins.late.techguns;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;

import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import techguns.client.renderer.entity.RenderTeslaBeam;

@Mixin(value = RenderTeslaBeam.class, priority = 999)
public class MixinRenderTeslaBeam {

    @WrapMethod(method = "doRender")
    private void dontCastShadow(Entity par1Entity, double par2, double par4, double par6, float par8, float par9,
        Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(par1Entity, par2, par4, par6, par8, par9);
        }
    }

}
