package com.kotmatross.shadersfixer.mixins.late.client.cnpc;

import com.kotmatross.shadersfixer.Utils;
import noppes.npcs.client.RenderChatMessages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderChatMessages.class, priority = 999)
public class MixinRenderChatMessages {
	@Inject(method = "drawRect", at = @At(value = "HEAD"), remap = false)
	public void drawRect(int par0, int par1, int par2, int par3, int par4, double par5, CallbackInfo ci)
	{
		Utils.Fix();
	}
}
