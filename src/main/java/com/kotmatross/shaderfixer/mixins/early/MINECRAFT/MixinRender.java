package com.kotmatross.shaderfixer.mixins.early.MINECRAFT;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = Render.class, priority = 999)
public class MixinRender {

    @Inject(method = "func_147906_a", at = @At(value = "HEAD"))
    protected void fixNametagRender(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_,
        double p_147906_7_, int p_147906_9_, CallbackInfo ci) {
        Utils.Fix();
    }
}
