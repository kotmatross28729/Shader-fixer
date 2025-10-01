package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity.projectile;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSpellWhip;
import com.fiskmods.heroes.common.entity.projectile.EntitySpellWhip;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderSpellWhip.class, priority = 999)
public abstract class MixinRenderSpellWhip extends Render {

    @Inject(
        method = "doRender",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void doRender(EntitySpellWhip entity, double x, double y, double z, float entityYaw, float partialTicks,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "doRender",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void doRender2(EntitySpellWhip entity, double x, double y, double z, float entityYaw, float partialTicks,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
