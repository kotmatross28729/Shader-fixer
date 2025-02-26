package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.util.DiamondPronter;
import com.hbm.render.util.EnumSymbol;
import com.kotmatross.shadersfixer.AngelicaUtils;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DiamondPronter.class, priority = 999)
public class MixinDiamondPronter {
	@Inject(method = "pront",
			slice = @Slice(from = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
					ordinal = 0),
					to = @At(value = "INVOKE",
							target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
							ordinal = 4)),
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", shift = At.Shift.AFTER))
	private static void fixLightingWithShaders(int poison, int flammability, int reactivity, EnumSymbol symbol, CallbackInfo ci, @Local Tessellator tess) {
		if(AngelicaUtils.isShaderEnabled()) {
			tess.setNormal(1, 0, 0);
		}
	}
}
