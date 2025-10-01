package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.common.tileentity.TileEntitySuitDatabase;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = com.fiskmods.heroes.client.render.tile.RenderSuitDatabase.class, priority = 999)
public abstract class MixinRenderSuitDatabase extends TileEntitySpecialRenderer {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void render(TileEntitySuitDatabase tile, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            shift = At.Shift.AFTER))
    public void render2(TileEntitySuitDatabase tile, double x, double y, double z, float partialTicks,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }
}
