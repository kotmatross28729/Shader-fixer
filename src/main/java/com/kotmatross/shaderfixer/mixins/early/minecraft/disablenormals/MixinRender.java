package com.kotmatross.shaderfixer.mixins.early.minecraft.disablenormals;

import net.minecraft.client.renderer.entity.Render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = Render.class, priority = 1009)
public class MixinRender {

    @WrapWithCondition(
        method = "func_147906_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glNormal3f(FFF)V", remap = false))
    private boolean disableNormalsSetup(float nx, float ny, float nz) {
        return false; // Peak mjoang coding v2
    }

}
