package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.main.ResourceManager;
import com.hbm.render.loader.HFRWavefrontObject;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ResourceManager.class, priority = 999)
public class MixinResourceManager {
	
	/// Bob, what the hell?
	@WrapOperation(method = "<clinit>"
			, at = @At(value = "INVOKE"
					, target = "Lnet/minecraftforge/client/model/AdvancedModelLoader;loadModel(Lnet/minecraft/util/ResourceLocation;)Lnet/minecraftforge/client/model/IModelCustom;")
	)
	private static IModelCustom clinit(ResourceLocation resource, Operation<IModelCustom> original) {
		return new HFRWavefrontObject(resource);
	}
	
}

