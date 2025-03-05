package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.tileentity.RenderPylonBase;
import com.kotmatross.shadersfixer.ShadersFixer;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPylonBase.class, priority = 999)
public class MixinRenderPylonBase {
	@Inject(method = "drawLineSegment",
			at = @At(value = "HEAD"), remap = false)
	private void drawLineSegment(Tessellator tessellator, double x, double y, double z, double a, double b, double c, double iX, double iY, double iZ, double jX, double jZ, CallbackInfo ci) {
		if(ShadersFixer.IS_ANGELICA_PRESENT) {
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
		}
	}
}
