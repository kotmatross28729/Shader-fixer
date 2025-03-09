package com.kotmatross.shadersfixer.mixins.early.client.special;

import net.coderbot.iris.gui.screen.ShaderPackScreen;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ShaderPackScreen.class, priority = 999)
public class MixinShaderPackScreen {

    // "target was loaded too early"

    @Inject(method = "drawScreen", at = @At(value = "HEAD"))
    public void drawScreen(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Inject(method = "drawScreen", at = @At(value = "TAIL"))
    public void drawScreen2(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        GL11.glPopAttrib();
    }
}
