package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IWorldAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderGlobal.class, priority = 999)
public abstract class MixinRenderGlobal implements IWorldAccess {

    // Hitbox (F3+B)
    @Inject(method = "drawOutlinedBoundingBox", at = @At(value = "HEAD"))
    private static void drawOutlinedBoundingBox(AxisAlignedBB p_147590_0_, int p_147590_1_, CallbackInfo ci) {
        Utils.Fix();
    }
}
