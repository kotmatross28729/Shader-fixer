package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.render.tile.RenderSuitFabricator.class, priority = 999)
public abstract class MixinRenderSuitFabricator extends TileEntitySpecialRenderer {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(com.fiskmods.heroes.common.tileentity.TileEntitySuitFabricator tile, double x, double y,
        double z, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
