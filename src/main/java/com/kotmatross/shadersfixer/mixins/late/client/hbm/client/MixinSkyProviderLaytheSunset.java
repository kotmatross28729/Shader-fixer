package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.dim.laythe.SkyProviderLaytheSunset;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SkyProviderLaytheSunset.class, priority = 999)
public class MixinSkyProviderLaytheSunset {
    @Inject(method = "renderSunset",
        at = @At(value = "HEAD"), remap = false)
    private void renderSunset(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.Fix2();
    }
}
