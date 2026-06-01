package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.loader.HFRWavefrontObjectVBO;
import com.kotmatross.shaderfixer.shrimp.nonsense.DoubleFuckingCursedAward;
import com.kotmatross.shaderfixer.shrimp.ntm.ModelShadowProxyExtended;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@DoubleFuckingCursedAward
@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(value = HFRWavefrontObjectVBO.class, priority = 999, remap = false)
public class MixinHFRWavefrontObjectVBO {

	@Unique
	public IModelCustom shadowProxyModelVBO;
	
	@Inject(method = "<init>(Lcom/hbm/render/loader/HFRWavefrontObject;)V"
			, at = @At(value = "TAIL"))
	private void init(HFRWavefrontObject obj, CallbackInfo ci) {
		shadowProxyModelVBO = ((ModelShadowProxyExtended)obj).getShadowProxy();
	}
	
	@WrapMethod(method = "renderAll")
	private void renderAll(Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModelVBO != null) {
			shadowProxyModelVBO.renderAll();
		} else {
			original.call();
		}
	}
	
	@WrapMethod(method = "renderOnly")
	private void renderOnly(String[] groupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModelVBO != null) {
			shadowProxyModelVBO.renderOnly(groupNames);
		} else {
			original.call((Object)groupNames);
		}
	}
	
	@WrapMethod(method = "renderPart")
	private void renderPart(String partName, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModelVBO != null) {
			shadowProxyModelVBO.renderPart(partName);
		} else {
			original.call(partName);
		}
	}
	
	@WrapMethod(method = "renderAllExcept")
	private void renderAllExcept(String[] excludedGroupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModelVBO != null) {
			shadowProxyModelVBO.renderAllExcept(excludedGroupNames);
		} else {
			original.call((Object) excludedGroupNames);
		}
	}
	
}

