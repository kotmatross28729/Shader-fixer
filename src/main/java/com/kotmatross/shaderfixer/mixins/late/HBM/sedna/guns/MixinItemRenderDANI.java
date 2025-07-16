package com.kotmatross.shaderfixer.mixins.late.HBM.sedna.guns;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.main.ResourceManager;
import com.hbm.render.item.weapon.sedna.ItemRenderDANI;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = ItemRenderDANI.class, priority = 999)
public class MixinItemRenderDANI {

    @Inject(
        method = "renderFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/render/item/weapon/sedna/ItemRenderDANI;renderSmokeNodes(Ljava/util/List;D)V",
            shift = At.Shift.AFTER),
        remap = false)
    public void fixAfterSmoke(ItemStack stack, CallbackInfo ci, @Local(ordinal = 1) int index) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(index == 0 ? ResourceManager.dani_celestial_tex : ResourceManager.dani_lunar_tex);
    }

}
