package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.render.tileentity.RenderCatalyticCracker;
import com.hbm.render.tileentity.RenderChimneyBrick;
import com.hbm.render.tileentity.RenderChimneyIndustrial;
import com.hbm.render.tileentity.RenderCoker;
import com.hbm.render.tileentity.RenderFrackingTower;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ShadowModel;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = {RenderChimneyBrick.class
		, RenderChimneyIndustrial.class
		, RenderCoker.class
		, RenderCatalyticCracker.class
		, RenderFrackingTower.class
}
		, priority = 999)
public class SelfShadowingUberMixin {
	
	@ModifyReceiver(method = "renderTileEntityAt"
			, at = @At(value = "INVOKE"
				, target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V"))
	private IModelCustom fixModel(IModelCustom instance) {
		if (AngelicaUtils_WRAPPER.isShadowPass()) {
			int res = AngelicaUtils_WRAPPER.getShadowMapResolution();
			ShadowModel smodel = ShadowModel.getByClass(this.getClass());
			if (smodel != null) {
				return smodel.getModel(res, instance);
			}
		}
		return instance;
	}
	
}
