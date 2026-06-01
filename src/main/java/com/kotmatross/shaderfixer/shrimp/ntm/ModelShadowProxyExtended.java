package com.kotmatross.shaderfixer.shrimp.ntm;

import com.kotmatross.shaderfixer.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
public interface ModelShadowProxyExtended {
	HFRWavefrontObjectShadowProxy getProxy();
	
	static ResourceLocation hijackResource(ResourceLocation resource) {
		return new ResourceLocation(Tags.MODID, resource.getResourcePath());
	}
	static InputStream hijackResourceToStream(ResourceLocation resource) throws IOException {
		return Minecraft.getMinecraft().getResourceManager()
				.getResource(hijackResource(resource)).getInputStream();
	}
	
}
