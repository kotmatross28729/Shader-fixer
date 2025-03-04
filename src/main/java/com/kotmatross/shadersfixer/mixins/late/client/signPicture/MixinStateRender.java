package com.kotmatross.shadersfixer.mixins.late.client.signPicture;

import com.kamesuta.mc.bnnwidget.render.OpenGL;
import com.kamesuta.mc.signpic.entry.content.Content;
import com.kamesuta.mc.signpic.render.StateRender;
import com.kamesuta.mc.signpic.state.Progress;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;

@Mixin(value = StateRender.class, priority = 999)
public class MixinStateRender {
	@Inject(method = "drawLoading", at = @At(value = "HEAD"), remap = false)
	private static void drawLoading(final @Nonnull Progress progress, final @Nonnull StateRender.LoadingCircle type, final @Nonnull StateRender.LoadingCircleType speed, CallbackInfo ci) {
		Utils.Fix();
	}
	
	@Inject(method = "drawMessage", at = @At(value = "HEAD"), remap = false)
	private static void drawMessage(@Nonnull Content content, @Nonnull FontRenderer fontrenderer, CallbackInfo ci) {
		OpenGL.glPushAttrib(GL11.GL_DEPTH_BUFFER_BIT);
		OpenGL.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	@Inject(method = "drawMessage", at = @At(value = "TAIL"), remap = false)
	private static void drawMessage2(@Nonnull Content content, @Nonnull FontRenderer fontrenderer, CallbackInfo ci) {
		OpenGL.glEnable(GL11.GL_DEPTH_TEST);
		OpenGL.glPopAttrib();
	}
}
