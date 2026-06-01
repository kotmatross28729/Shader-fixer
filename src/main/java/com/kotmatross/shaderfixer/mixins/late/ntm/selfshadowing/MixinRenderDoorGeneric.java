package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.animloader.AnimatedModel;
import com.hbm.animloader.ColladaLoader;
import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderDoorGeneric;
import com.hbm.tileentity.DoorDecl;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

/**
 * I'm so NOT doing this for the fucking collada loader
 */
@Mixin(value = RenderDoorGeneric.class, priority = 999)
public class MixinRenderDoorGeneric {
	
	@Unique
	private static final AnimatedModel transition_seal_SHADOW_PROXY = ColladaLoader.load(new ResourceLocation(Tags.MODID, "models/doors/seal.dae"), true);
	
	@WrapOperation(method = "renderTileEntityAt"
			, at = @At(value = "INVOKE"
				, target = "Lcom/hbm/tileentity/DoorDecl;getAnimatedModel()Lcom/hbm/animloader/AnimatedModel;")
	)
	private static AnimatedModel renderTileEntityAt(DoorDecl instance, Operation<AnimatedModel> original) {
		AnimatedModel orig = original.call(instance);
		if(orig == ResourceManager.transition_seal && AngelicaUtils_WRAPPER.isShadowPass()) {
			return transition_seal_SHADOW_PROXY;
		} else {
			return orig;
		}
	}
	
}
