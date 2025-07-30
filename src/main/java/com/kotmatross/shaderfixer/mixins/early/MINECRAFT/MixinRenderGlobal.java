package com.kotmatross.shaderfixer.mixins.early.MINECRAFT;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IWorldAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = RenderGlobal.class, priority = 1009)
public abstract class MixinRenderGlobal implements IWorldAccess {

    // I fucking hate this thing
    @WrapWithCondition(
        method = "renderSky",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glCallList(I)V", ordinal = 3))
    private boolean disableFuckingHorizon(int i) {
        return !ShaderFixerConfig.VANILLA_DISABLE_HORIZON;
    }

    // Hitbox (F3+B)
    @Inject(method = "drawOutlinedBoundingBox", at = @At(value = "HEAD"))
    private static void fixHitboxRender(AxisAlignedBB p_147590_0_, int p_147590_1_, CallbackInfo ci) {
        Utils.Fix();
    }
}
