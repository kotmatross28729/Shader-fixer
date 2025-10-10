package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import net.minecraft.entity.projectile.EntityArrow;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import fox.spiteful.avaritia.render.RenderHeavenArrow;

@Mixin(value = RenderHeavenArrow.class, priority = 999)
public class MixinRenderHeavenArrow {

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
    }

    @Inject(method = "func_76986_a", at = @At(value = "TAIL"), remap = false)
    private void func_76986_a2(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_,
        float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
