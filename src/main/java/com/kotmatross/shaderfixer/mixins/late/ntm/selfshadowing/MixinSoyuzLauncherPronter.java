package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.main.ResourceManager;
import com.hbm.render.util.SoyuzLauncherPronter;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ModelsSelfShadowingFix;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraftforge.client.model.IModelCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = SoyuzLauncherPronter.class, priority = 999, remap = false)
public class MixinSoyuzLauncherPronter {
	
	@ModifyReceiver(method = "prontLauncher"
			, at = @At(value = "INVOKE"
				, target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V"))
	private static IModelCustom fixModel(IModelCustom instance) {
		if (AngelicaUtils_WRAPPER.isShadowPass()) {
			if(instance == ResourceManager.soyuz_launcher_legs)
				return ModelsSelfShadowingFix.soyuz_launcher_legs;
			if(instance == ResourceManager.soyuz_launcher_table)
				return ModelsSelfShadowingFix.soyuz_launcher_table;
			if(instance == ResourceManager.soyuz_launcher_tower)
				return ModelsSelfShadowingFix.soyuz_launcher_tower;
			if(instance == ResourceManager.soyuz_launcher_support)
				return ModelsSelfShadowingFix.soyuz_launcher_support;
		}
		return instance;
	}
	
}
