package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.fiskmods.heroes.client.render.entity.projectile.RenderEnergyBolt;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;

@Mixin(value = RenderEnergyBolt.class, priority = 999)
public class MixinRenderEnergyBolt {

    @ModifyArg(
        method = "renderBolt",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V"),
        index = 3,
        remap = false)
    private double alphaFix(double alpha) {
        return AngelicaUtils.isShaderEnabled() ? Math.max(alpha, 0.1) : alpha;
    }

}
