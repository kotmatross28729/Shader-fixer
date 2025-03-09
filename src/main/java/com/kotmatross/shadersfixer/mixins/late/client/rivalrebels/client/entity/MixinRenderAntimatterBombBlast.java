package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import rivalrebels.client.renderentity.RenderAntimatterBombBlast;

@Mixin(value = RenderAntimatterBombBlast.class, priority = 999)
public class MixinRenderAntimatterBombBlast {

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity var1, double x, double y, double z, float var8, float var9, CallbackInfo ci) {
        Utils.Fix();
    }
}
