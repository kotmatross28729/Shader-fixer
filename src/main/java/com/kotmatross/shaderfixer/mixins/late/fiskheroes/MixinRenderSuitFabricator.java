package com.kotmatross.shaderfixer.mixins.late.fiskheroes;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.tile.RenderSuitFabricator;
import com.kotmatross.shaderfixer.utils.ShaderUtils;

@Mixin(value = RenderSuitFabricator.class, priority = 999)
public abstract class MixinRenderSuitFabricator extends TileEntitySpecialRenderer {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void render(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void render2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }
}
