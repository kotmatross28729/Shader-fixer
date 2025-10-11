package com.kotmatross.shaderfixer.mixins.late.AVARITIA;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import fox.spiteful.avaritia.render.ShaderHelper;

@Mixin(value = CosmicRenderShenanigans.class, priority = 999)
public class MixinCosmicRenderShenanigans {

    @Unique
    private static int shader_fixer$previousProgram;

    @Inject(method = "useShader", at = @At(value = "HEAD"), remap = false)
    private static void useShader(CallbackInfo ci) {
        shader_fixer$previousProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    @Inject(method = "releaseShader", at = @At(value = "TAIL"), remap = false)
    private static void releaseShader(CallbackInfo ci) {
        ShaderHelper.useShader(shader_fixer$previousProgram);
    }

}
