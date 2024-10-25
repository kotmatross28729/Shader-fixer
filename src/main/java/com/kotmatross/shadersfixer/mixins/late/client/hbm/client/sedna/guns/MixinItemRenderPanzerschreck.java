package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna.guns;

import com.hbm.render.item.weapon.sedna.ItemRenderPanzerschreck;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import org.spongepowered.asm.mixin.Mixin;

import java.lang.annotation.Annotation;

@Mixin(value = ItemRenderPanzerschreck.class, priority = 999)
public class MixinItemRenderPanzerschreck implements Vibe {
    @Override
    public Class<? extends Annotation> annotationType() {
        return Vibe.class;
    }
}
