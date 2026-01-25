package com.kotmatross.shaderfixer.mixins.late.techguns;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import techguns.blocks.BlockTGChest;
import techguns.client.renderer.tileentity.RenderTGChest;
import techguns.tileentities.TGChestTileEnt;

@Mixin(value = RenderTGChest.class, priority = 999)
public class MixinRenderTGChest {

    @Inject(
        method = "renderTileEntityAt(Ltechguns/tileentities/TGChestTileEnt;DDDF)V",
        at = @At(value = "HEAD"),
        cancellable = true,
        remap = false)
    public void renderTileEntityAt(CallbackInfo ci, @Local(argsOnly = true) TGChestTileEnt chestTileEnt) {
        if (!(chestTileEnt.getBlockType() instanceof BlockTGChest)) {
            ci.cancel();
        }
    }

}
