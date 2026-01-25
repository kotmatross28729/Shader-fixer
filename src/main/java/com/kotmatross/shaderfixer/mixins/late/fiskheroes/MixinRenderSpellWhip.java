package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSpellWhip;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderSpellWhip.class, priority = 999)
public abstract class MixinRenderSpellWhip extends Render {

    @Inject(
        method = "doRender",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    public void doRender(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "doRender",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void doRender2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
