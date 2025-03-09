package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.effect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import com.fiskmods.heroes.client.render.effect.Effect;
import com.fiskmods.heroes.client.render.effect.EffectTentacles;

@Mixin(value = EffectTentacles.class, priority = 999)
public abstract class MixinEffectTentacles implements Effect {

    @ModifyConstant(method = "renderTentacle", constant = @Constant(intValue = 2896), remap = false)
    private static int renderTentacle(int constant) {
        return -1;
    }
}
