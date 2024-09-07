package com.kotmatross.shadersfixer.mixins.late.client.eln.client;

import com.kotmatross.shadersfixer.Utils;
import mods.eln.sixnode.electricaldatalogger.DataLogs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DataLogs.class, priority = 999)
public class MixinDataLogs {
    @Inject(method = "draw([BIFFFBFFLjava/lang/String;)V", at = @At(value = "HEAD"), remap = false)
    private static void draw(byte[] value, int size, float samplingPeriod, float maxValue, float minValue, byte unitType, float margeX, float margeY, String textHeader, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
