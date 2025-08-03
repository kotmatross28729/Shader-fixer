package com.kotmatross.shaderfixer.mixins.late.HBM;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.blocks.generic.BlockRebar;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = BlockRebar.class, priority = 999)
public class MixinBlockRebar {

    @Inject(
        method = "renderRebar",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            shift = At.Shift.BEFORE))
    private static void renderRebar(List tiles, float interp, CallbackInfo ci) {
        Utils.Fix();
    }

}
