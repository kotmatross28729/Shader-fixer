package com.kotmatross.shadersfixer.mixins.late.client.mapleTree;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import ecru.MapleTree.tile.ecru_TileEntityLighthouseIlluminationRender;

@Mixin(value = ecru_TileEntityLighthouseIlluminationRender.class, priority = 999)
public class Mixin_ecru_TileEntityLighthouseIlluminationRender {
    // I tried program-fix but it works VERY weird here

    @Inject(method = "renderIllumination3", at = @At(value = "HEAD"), remap = false)
    protected void renderIllumination3(TileEntity par1TileEntity, float ti, double i, double j, double k,
        CallbackInfo ci) {
        Utils.Fix();
    }

}
