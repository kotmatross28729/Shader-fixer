package com.kotmatross.shaderfixer.mixins.late.TECHGUNS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import techguns.blocks.BlockTGChest;
import techguns.client.renderer.tileentity.RenderTGChest;
import techguns.tileentities.TGChestTileEnt;

@Mixin(value = RenderTGChest.class, priority = 999)
public class MixinRenderTGChest {

    @Inject(method = "renderTileEntityAt", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void renderTileEntityAt(TGChestTileEnt chestTileEnt, double p_147500_2_, double p_147500_4_,
        double p_147500_6_, float p_147500_8_, CallbackInfo ci) {
        if (!(chestTileEnt.getBlockType() instanceof BlockTGChest)) {
            ci.cancel();
        }
    }
}
