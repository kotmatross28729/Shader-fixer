package com.kotmatross.shaderfixer.mixins.late.HBM.sedna.guns;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.item.weapon.sedna.ItemRenderNI4NI;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ItemRenderNI4NI.class, priority = 999)
public class MixinItemRenderNI4NI {

    @Inject(
        method = "renderFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", shift = At.Shift.AFTER),
        remap = false)
    public void fix(ItemStack stack, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "renderOther",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", shift = At.Shift.AFTER),
        remap = false)
    public void fix(ItemStack stack, IItemRenderer.ItemRenderType type, CallbackInfo ci) {
        Utils.Fix();
    }

}
