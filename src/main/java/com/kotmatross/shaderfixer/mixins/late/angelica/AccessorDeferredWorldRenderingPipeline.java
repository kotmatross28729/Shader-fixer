package com.kotmatross.shaderfixer.mixins.late.angelica;

import net.coderbot.iris.pipeline.DeferredWorldRenderingPipeline;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DeferredWorldRenderingPipeline.class)
public interface AccessorDeferredWorldRenderingPipeline {
	
	@Accessor(remap = false)
	int getShadowMapResolution();
	
}
