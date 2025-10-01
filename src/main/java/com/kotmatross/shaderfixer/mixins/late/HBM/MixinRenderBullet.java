package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderBullet;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderBullet.class, priority = 999)
public class MixinRenderBullet {

    @Inject(method = "renderDart", at = @At(value = "HEAD"), remap = false)
    public void renderDart(int style, int eID, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderDart", at = @At(value = "TAIL"), remap = false)
    public void renderDart2(int style, int eID, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderTau", at = @At(value = "HEAD"), remap = false)
    public void renderTau(Entity bullet, int trail, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "renderTau", at = @At(value = "TAIL"), remap = false)
    public void renderTau2(Entity bullet, int trail, float interp, CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
