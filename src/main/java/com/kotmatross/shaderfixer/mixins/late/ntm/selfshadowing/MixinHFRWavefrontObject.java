package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.loader.HFRWavefrontObjectVBO;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.shrimp.nonsense.DoubleFuckingCursedAward;
import com.kotmatross.shaderfixer.shrimp.ntm.HFRWavefrontObjectShadowProxy;
import com.kotmatross.shaderfixer.shrimp.ntm.ModelShadowProxyExtended;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.ModelFormatException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@DoubleFuckingCursedAward
@SuppressWarnings({"AddedMixinMembersNamePattern"})
@Mixin(value = HFRWavefrontObject.class, priority = 999, remap = false)
public class MixinHFRWavefrontObject implements ModelShadowProxyExtended {
	
	@Unique
	public IModelCustom shadowProxyModel;
	
	@Override
	public IModelCustom getShadowProxy() {
		return shadowProxyModel;
	}
	
	@Inject(method = "<init>(Lnet/minecraft/util/ResourceLocation;Z)V"
			, at = @At(value = "TAIL"))
	private void init(ResourceLocation resource, boolean mixedMode, CallbackInfo ci) {
		try {
			ResourceLocation hijack = new ResourceLocation(Tags.MODID, resource.getResourcePath());
			shadowProxyModel = new HFRWavefrontObjectShadowProxy(hijack, mixedMode);
		} catch(ModelFormatException ignored) {}
	}
	
	@WrapMethod(method = "renderAll")
	private void renderAll(Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			shadowProxyModel.renderAll();
		} else {
			original.call();
		}
	}
	
	@WrapMethod(method = "tessellateAll")
	private void tessellateAll(Tessellator tessellator, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			((HFRWavefrontObject)shadowProxyModel).tessellateAll(tessellator);
		} else {
			original.call(tessellator);
		}
	}
	
	@WrapMethod(method = "renderOnly")
	private void renderOnly(String[] groupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			shadowProxyModel.renderOnly(groupNames);
		} else {
			original.call((Object) groupNames);
		}
	}
	
	@WrapMethod(method = "tessellateOnly")
	private void tessellateOnly(Tessellator tessellator, String[] groupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			((HFRWavefrontObject)shadowProxyModel).tessellateOnly(tessellator, groupNames);
		} else {
			original.call(tessellator, groupNames);
		}
	}
	
	
	@WrapMethod(method = "renderPart")
	private void renderPart(String partName, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			shadowProxyModel.renderPart(partName);
		} else {
			original.call(partName);
		}
	}
	
	@WrapMethod(method = "tessellatePart")
	private void tessellatePart(Tessellator tessellator, String partName, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			((HFRWavefrontObject)shadowProxyModel).tessellatePart(tessellator, partName);
		} else {
			original.call(tessellator, partName);
		}
	}
	
	@WrapMethod(method = "renderAllExcept")
	private void renderAllExcept(String[] excludedGroupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			shadowProxyModel.renderAllExcept(excludedGroupNames);
		} else {
			original.call((Object) excludedGroupNames);
		}
	}
	
	@WrapMethod(method = "tessellateAllExcept")
	private void tessellateAllExcept(Tessellator tessellator, String[] excludedGroupNames, Operation<Void> original) {
		if (AngelicaUtils_WRAPPER.isShadowPass() && shadowProxyModel != null) {
			((HFRWavefrontObject)shadowProxyModel).tessellateAllExcept(tessellator, excludedGroupNames);
		} else {
			original.call(tessellator, excludedGroupNames);
		}
	}

	@Inject(method = "asVBO", at = @At(value = "HEAD"))
	private void asVBO(CallbackInfoReturnable<HFRWavefrontObjectVBO> cir) {
		if(shadowProxyModel != null)
			shadowProxyModel = ((HFRWavefrontObjectShadowProxy)shadowProxyModel).asVBO();
	}

}
