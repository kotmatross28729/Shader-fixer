package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import rivalrebels.client.renderentity.RenderLaserBurst;
import rivalrebels.common.entity.EntityLaserBurst;
import com.fiskmods.heroes.common.entity.projectile.EntityEnergyBolt;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderLaserBurst.class, priority = 999)
public class MixinRenderLaserBurst {
    @Inject(method = "renderLaserBurst*",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 3)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderLaserBurst(EntityLaserBurst ell, double x, double y, double z, float yaw, float pitch, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity entityLaserBurst, double x, double y, double z, float yaw, float pitch, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
