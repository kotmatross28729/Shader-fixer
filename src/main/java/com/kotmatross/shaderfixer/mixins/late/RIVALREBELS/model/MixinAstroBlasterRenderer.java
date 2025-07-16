package com.kotmatross.shaderfixer.mixins.late.RIVALREBELS.model;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import rivalrebels.client.itemrenders.AstroBlasterRenderer;

@Mixin(value = AstroBlasterRenderer.class, priority = 999)
public class MixinAstroBlasterRenderer {

    @Inject(
        method = "renderItem*",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 3)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
