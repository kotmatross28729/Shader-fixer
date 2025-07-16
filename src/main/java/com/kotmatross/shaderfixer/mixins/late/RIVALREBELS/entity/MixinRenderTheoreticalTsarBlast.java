package com.kotmatross.shaderfixer.mixins.late.RIVALREBELS.entity;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import rivalrebels.client.renderentity.RenderTheoreticalTsarBlast;

@Mixin(value = RenderTheoreticalTsarBlast.class, priority = 999)
public class MixinRenderTheoreticalTsarBlast {

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity var1, double x, double y, double z, float var8, float var9, CallbackInfo ci) {
        Utils.Fix();
    }
}
