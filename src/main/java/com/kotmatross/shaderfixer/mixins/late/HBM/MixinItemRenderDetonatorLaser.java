package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.item.ItemRenderDetonatorLaser;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ItemRenderDetonatorLaser.class, priority = 999)
public class MixinItemRenderDetonatorLaser {

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.fix();
    }

    @Inject(
        method = "renderItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderItem2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.fix();
    }

}
