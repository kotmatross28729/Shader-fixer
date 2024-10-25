package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna.guns;

import com.hbm.render.item.weapon.sedna.ItemRenderQuadro;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import org.spongepowered.asm.mixin.Mixin;

import java.lang.annotation.Annotation;

@Mixin(value = ItemRenderQuadro.class, priority = 999)
public class MixinItemRenderQuadro implements Vibe {
    @Override
    public Class<? extends Annotation> annotationType() {
        return Vibe.class;
    }
}
