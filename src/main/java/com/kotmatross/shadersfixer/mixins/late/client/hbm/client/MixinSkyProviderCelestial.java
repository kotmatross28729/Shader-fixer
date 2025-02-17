package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.dim.SkyProviderCelestial;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SkyProviderCelestial.class, priority = 999)
public class MixinSkyProviderCelestial {
    
   @Inject(method = "render",
        at = @At(value = "HEAD"), remap = false)
    public void render(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.Fix2();
    }
    
    //TODO
    //  renderSun -> AngelicaUtils.isShaderEnabled() -> disable:
    //        tessellator.func_78382_b();
    //        tessellator.func_78377_a(-sunSize, 99.9, -sunSize);
    //        tessellator.func_78377_a(sunSize, 99.9, -sunSize);
    //        tessellator.func_78377_a(sunSize, 99.9, sunSize);
    //        tessellator.func_78377_a(-sunSize, 99.9, sunSize);
    //        tessellator.func_78381_a();
}

