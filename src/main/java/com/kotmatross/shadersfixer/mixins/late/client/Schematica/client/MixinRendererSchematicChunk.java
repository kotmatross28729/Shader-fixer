package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicChunk;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RendererSchematicChunk.class, priority = 999)
public class MixinRendererSchematicChunk {

    @Inject(
        method = "updateRenderer",
        slice = @Slice(
            from = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0),
            to = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 0)),
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        remap = false)
    public void updateRenderer(CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program;

    @Inject(
        method = "updateRenderer",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        remap = false)
    public void updateRenderer$programS(CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "updateRenderer",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        remap = false)
    public void updateRenderer$programE(CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }
}
