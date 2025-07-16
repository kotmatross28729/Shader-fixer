package com.kotmatross.shaderfixer.mixins.late.ELN;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;

import mods.eln.sixnode.electricaldatalogger.DataLogs;

@Mixin(value = DataLogs.class, priority = 999)
public class MixinDataLogs {

    @Inject(method = "draw([BIFFFBFFLjava/lang/String;)V", at = @At(value = "HEAD"), remap = false)
    private static void draw(byte[] value, int size, float samplingPeriod, float maxValue, float minValue,
        byte unitType, float margeX, float margeY, String textHeader, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
