package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicGlobal;
import com.github.lunatrius.schematica.client.world.SchematicWorld;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = RendererSchematicGlobal.class, priority = 999)
public class MixinRendererSchematicGlobal {

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render(SchematicWorld schematic, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render$programS(SchematicWorld schematic, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "render", at = @At(value = "TAIL"), remap = false)
    public void render$programE(SchematicWorld schematic, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
    }
}
