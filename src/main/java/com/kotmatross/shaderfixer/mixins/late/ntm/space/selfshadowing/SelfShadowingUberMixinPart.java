package com.kotmatross.shaderfixer.mixins.late.ntm.space.selfshadowing;

import com.hbm.render.tileentity.RenderDysonLauncher;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ModelsSelfShadowingFix;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = {
		  RenderDysonLauncher.class
}
		, priority = 999)
public class SelfShadowingUberMixinPart {
	
	@ModifyReceiver(method = "renderTileEntityAt"
			, at = @At(value = "INVOKE"
				, target = "Lnet/minecraftforge/client/model/IModelCustom;renderPart(Ljava/lang/String;)V"
				, remap = false))
	private IModelCustom fixModelPart(IModelCustom instance, String s) {
		if (AngelicaUtils_WRAPPER.isShadowPass()) {
			return ModelsSelfShadowingFix.getByClass(this.getClass());
		}
		return instance;
	}
	
}
