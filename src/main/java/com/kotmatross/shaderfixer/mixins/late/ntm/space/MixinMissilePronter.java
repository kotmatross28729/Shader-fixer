package com.kotmatross.shaderfixer.mixins.late.ntm.space;

import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.MissilePronter;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@SPEKJORK
@Mixin(value = MissilePronter.class, priority = 999)
public class MixinMissilePronter {

    @Inject(
        method = "prontRocket(Lcom/hbm/handler/RocketStruct;Lcom/hbm/entity/missile/EntityRideableRocket;Lnet/minecraft/client/renderer/texture/TextureManager;ZIIF)V",
        at = @At(value = "HEAD"),
        remap = false)
    private static void fixZFighting0(CallbackInfo ci) {
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
    }

    @WrapOperation(
        method = "prontRocket(Lcom/hbm/handler/RocketStruct;Lcom/hbm/entity/missile/EntityRideableRocket;Lnet/minecraft/client/renderer/texture/TextureManager;ZIIF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V", ordinal = 0),
        remap = false)
    private static void fixZFighting1(IModelCustom instance, Operation<Void> original) {
        GL11.glPolygonOffset(4.0f, 4.0f);
        original.call(instance);
        GL11.glPolygonOffset(0.0f, 0.0f);
    }

    @Inject(
        method = "prontRocket(Lcom/hbm/handler/RocketStruct;Lcom/hbm/entity/missile/EntityRideableRocket;Lnet/minecraft/client/renderer/texture/TextureManager;ZIIF)V",
        at = @At(value = "TAIL"),
        remap = false)
    private static void fixZFighting2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
    }

}
