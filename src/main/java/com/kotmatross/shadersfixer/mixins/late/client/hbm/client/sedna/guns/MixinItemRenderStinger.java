package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna.guns;

import com.hbm.render.item.weapon.sedna.ItemRenderStinger;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import org.spongepowered.asm.mixin.Mixin;

import java.lang.annotation.Annotation;

@Mixin(value = ItemRenderStinger.class, priority = 999)
public class MixinItemRenderStinger implements Vibe {
    @Override
    public Class<? extends Annotation> annotationType() {
        return Vibe.class;
    }
}

