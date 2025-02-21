package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.dim.laythe.SkyProviderLaytheSunset;
import com.kotmatross.shadersfixer.ShadersFixer;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = SkyProviderLaytheSunset.class, priority = 999)
public class MixinSkyProviderLaytheSunset {
	//FOR NTM:SPACE
	
	//Fix angelica shader menu breaking shit
	@WrapWithCondition(
			method = "renderSunset",
			at = @At(value = "INVOKE", target = 
					"Lnet/minecraft/client/renderer/OpenGlHelper;glBlendFunc(IIII)V")
	)
	private boolean disableBlendFuncAngelica(int p_148821_0_, int p_148821_1_, int p_148821_2_, int p_148821_3_) {
		return !ShadersFixer.IS_ANGELICA_PRESENT;
	}
}
