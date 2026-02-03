package com.kotmatross.shaderfixer.mixins.late.schematica;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicChunk;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RendererSchematicChunk.class, priority = 999)
public class MixinRendererSchematicChunk {

    @Inject(
        method = "updateRenderer",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"),
        remap = false)
    public void updateRenderer$programS(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        shader_fixer$program.set(ShaderUtils.getCurrentProgram());
        ShaderUtils.useDefaultProgram();
    }

    @Inject(
        method = "updateRenderer",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"),
        remap = false)
    public void updateRenderer$programE(CallbackInfo ci,
        @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
        ShaderUtils.useProgram(shader_fixer$program.get());
    }

}
