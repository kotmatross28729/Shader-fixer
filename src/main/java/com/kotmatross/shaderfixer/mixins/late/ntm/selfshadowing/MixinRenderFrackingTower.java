package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.render.tileentity.RenderFrackingTower;
import com.kotmatross.shaderfixer.utils.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ModelsSelfShadowingFix;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RenderFrackingTower.class, priority = 999)
public class MixinRenderFrackingTower {
	
	@ModifyReceiver(method = "renderTileEntityAt"
			, at = @At(value = "INVOKE"
					, target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V"))
	private IModelCustom fixModel(IModelCustom instance) {
		if (AngelicaUtils_WRAPPER.isShadowPass()) {
			return ModelsSelfShadowingFix.fracking_tower;
		}
		return instance;
	}
	
}
