package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.space;

import net.minecraft.client.renderer.texture.TextureManager;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.missile.EntityRideableRocket;
import com.hbm.handler.RocketStruct;
import com.hbm.render.util.MissilePronter;
import com.kotmatross.shadersfixer.shrimp.SPEKJORK;

@SPEKJORK
@Mixin(value = MissilePronter.class, priority = 999)
public class MixinMissilePronter {

    @Inject(
        method = "prontRocket(Lcom/hbm/handler/RocketStruct;Lcom/hbm/entity/missile/EntityRideableRocket;Lnet/minecraft/client/renderer/texture/TextureManager;ZF)V",
        at = @At(
            value = "INVOKE",
            target = "org/lwjgl/opengl/GL11.glEnable (I)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private static void prontRocket(RocketStruct rocket, EntityRideableRocket entity, TextureManager tex,
        boolean isDeployed, float interp, CallbackInfo ci) {
        GL11.glPushMatrix();
        GL11.glScalef(0.97F, 1.0F, 0.97F); // Actually 0.99F also works, but we take it with a reserve (0.99F may
                                           // flicker at some angles)
    }

    @Inject(
        method = "prontRocket(Lcom/hbm/handler/RocketStruct;Lcom/hbm/entity/missile/EntityRideableRocket;Lnet/minecraft/client/renderer/texture/TextureManager;ZF)V",
        at = @At(
            value = "INVOKE",
            target = "org/lwjgl/opengl/GL11.glDisable (I)V",
            ordinal = 0,
            shift = At.Shift.AFTER),
        remap = false)
    private static void prontRocket2(RocketStruct rocket, EntityRideableRocket entity, TextureManager tex,
        boolean isDeployed, float interp, CallbackInfo ci) {
        GL11.glPopMatrix();
    }

}
