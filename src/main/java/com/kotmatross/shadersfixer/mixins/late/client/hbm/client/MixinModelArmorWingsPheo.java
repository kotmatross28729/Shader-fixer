package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.model.ModelArmorWingsPheo;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModelArmorWingsPheo.class, priority = 999)
public class MixinModelArmorWingsPheo {
	
	@Inject(method = "renderFlame",
			at = @At(value = "HEAD")
			, remap = false)
	private static void renderFlame(CallbackInfo ci) {
		Utils.Fix();
	}
	
}
