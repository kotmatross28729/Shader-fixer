package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderRainbow;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderRainbow.class, priority = 999)
public class MixinRenderRainbow {

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    public void func_76986_a(Entity rocket, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "func_76986_a", at = @At(value = "TAIL"), remap = false)
    public void func_76986_a2(Entity rocket, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
