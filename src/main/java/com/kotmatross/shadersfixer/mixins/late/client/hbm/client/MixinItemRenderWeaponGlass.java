package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.item.weapon.ItemRenderWeaponGlass;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderWeaponGlass.class, priority = 999)
public class MixinItemRenderWeaponGlass {

    @Inject(method = "renderItem",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderItem",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderItem2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }

    @Redirect(method = "renderItem",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V"),
        require = 1, remap = false)
    public void Ignore(boolean flag) {}
}

