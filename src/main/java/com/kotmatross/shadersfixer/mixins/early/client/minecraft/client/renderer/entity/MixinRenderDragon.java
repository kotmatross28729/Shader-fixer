package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.boss.EntityDragon;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderDragon.class, priority = 999)
public abstract class MixinRenderDragon {

    // Dragon death beams
    @Inject(
        method = "renderEquippedItems",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderEquippedItems(EntityDragon p_77029_1_, float p_77029_2_, CallbackInfo ci) {
        Utils.Fix();
    }
}
