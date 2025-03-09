package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicGlobal;
import com.github.lunatrius.schematica.client.world.SchematicWorld;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RendererSchematicGlobal.class, priority = 999)
public class MixinRendererSchematicGlobal {

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render(SchematicWorld schematic, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Unique
    private static int shaders_fixer$program;

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render$programS(SchematicWorld schematic, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "render", at = @At(value = "TAIL"), remap = false)
    public void render$programE(SchematicWorld schematic, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }
}
