package com.kotmatross.shaderfixer.mixins.late.CUSTOMPLAYERMODELS;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.utils.Utils;
import com.tom.cpm.client.RetroGL;

@Mixin(targets = "com.tom.cpm.client.RetroGL$RenderStage")
public class MixinRenderStage {

    @Unique
    private int shader_fixer$currentTextureID;

    @Inject(method = "begin", at = @At(value = "HEAD"), remap = false)
    public void begin$getCurrentTexture(RetroGL.RetroTessellator buf, CallbackInfo ci) {
        this.shader_fixer$currentTextureID = Utils.getCurrentTextureID();
    }

    @Inject(
        method = "begin",
        at = @At(
            value = "INVOKE",
            target = "com/tom/cpm/client/RetroGL$RetroTessellator.begin (I)V",
            shift = At.Shift.BEFORE),
        remap = false)
    public void begin$fix(RetroGL.RetroTessellator buf, CallbackInfo ci) {
        // glIsEnabled is quite slow, but shouldn't be a problem with GLStateManager (I hope)
        if (!GL11.glIsEnabled(GL11.GL_TEXTURE_2D)) Utils.Fix();
    }

    @Inject(method = "end", at = @At(value = "HEAD"), remap = false)
    public void end$bindPreviousTexture(CallbackInfo ci) {
        // Same
        if (!GL11.glIsEnabled(GL11.GL_TEXTURE_2D)) Utils.bindTextureByID(shader_fixer$currentTextureID);
    }

}
