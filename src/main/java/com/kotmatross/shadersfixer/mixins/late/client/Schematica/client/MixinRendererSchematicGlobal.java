package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicGlobal;
import com.github.lunatrius.schematica.client.world.SchematicWorld;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RendererSchematicGlobal.class, priority = 999)
public class MixinRendererSchematicGlobal {
   @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
   public void render(SchematicWorld schematic, CallbackInfo ci) {
       Utils.Fix();
   }
}
