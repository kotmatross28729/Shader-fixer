package com.kotmatross.shaderfixer.mixins.late.MAPLETREE;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import ecru.MapleTree.tile.ecru_TileEntitySLightRender;

@Mixin(value = ecru_TileEntitySLightRender.class, priority = 999)
public class Mixin_ecru_TileEntitySLightRender {
    // I tried program-fix but it works VERY weird here

    @Inject(method = "renderIllumination", at = @At(value = "HEAD"), remap = false)
    protected void renderIllumination(TileEntity par1TileEntity, float power, double i, double j, double k,
        CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderIllumination2", at = @At(value = "HEAD"), remap = false)
    protected void renderIllumination2(TileEntity par1TileEntity, float power, double i, double j, double k,
        CallbackInfo ci) {
        Utils.Fix();
    }

}
