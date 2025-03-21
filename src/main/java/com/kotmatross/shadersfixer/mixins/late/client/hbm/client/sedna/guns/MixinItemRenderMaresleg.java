package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna.guns;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.item.weapon.sedna.ItemRenderMaresleg;

@Mixin(value = ItemRenderMaresleg.class, priority = 999)
public class MixinItemRenderMaresleg {

    @Shadow
    public ResourceLocation texture;

    @Inject(
        method = "renderFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/render/item/weapon/sedna/ItemRenderMaresleg;renderSmokeNodes(Ljava/util/List;D)V",
            shift = At.Shift.AFTER),
        remap = false)
    public void fixAfterSmoke(ItemStack stack, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }
}
