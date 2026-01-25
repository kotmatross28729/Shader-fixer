package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.hbm.render.item.ItemRendererMeteorSword;

@Mixin(value = ItemRendererMeteorSword.class, priority = 999)
public class MixinItemRendererMeteorSword {

    @Redirect(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderHelper;enableGUIStandardItemLighting()V"),
        require = 1)
    public void Ignore() {}
}
