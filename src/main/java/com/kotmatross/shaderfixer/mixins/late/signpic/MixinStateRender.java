package com.kotmatross.shaderfixer.mixins.late.signpic;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kamesuta.mc.signpic.render.StateRender;

@Mixin(value = StateRender.class, priority = 999)
public class MixinStateRender {

    @Inject(method = "drawMessage", at = @At(value = "HEAD"), remap = false)
    private static void drawMessage(CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }

    @Inject(method = "drawMessage", at = @At(value = "TAIL"), remap = false)
    private static void drawMessage2(CallbackInfo ci) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopAttrib();
    }

}
