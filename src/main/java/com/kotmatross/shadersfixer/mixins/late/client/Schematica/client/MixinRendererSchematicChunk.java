package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicChunk;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RendererSchematicChunk.class, priority = 999)
public class MixinRendererSchematicChunk {
    @Inject(method = "updateRenderer",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"), remap = false)
    public void updateRenderer(CallbackInfo ci) {
        Utils.Fix();
    }
}
