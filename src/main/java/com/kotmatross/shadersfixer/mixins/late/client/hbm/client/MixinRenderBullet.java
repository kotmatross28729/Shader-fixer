package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.projectile.RenderBullet;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderBullet.class, priority = 999)
public class MixinRenderBullet {

    @Inject(method = "renderDart", at = @At(value = "HEAD"), remap = false)
    public void renderDart(int style, int eID, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderTau", at = @At(value = "HEAD"), remap = false)
    public void renderTau(Entity bullet, int trail, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
