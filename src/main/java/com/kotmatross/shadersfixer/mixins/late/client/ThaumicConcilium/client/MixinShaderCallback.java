package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import com.ilya3point999k.thaumicconcilium.client.render.ShaderCallback;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;
import org.spongepowered.asm.mixin.Mixin;
@FuckingCursed
@Mixin(value = ShaderCallback.class, priority = 999)
public class MixinShaderCallback {
    /*
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "<init>", at = @At(value = "RETURN"), remap = false)
    private void one(CallbackInfo ci) {
        shaders_fixer$program = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"), remap = false)
    private void two(CallbackInfo ci) {
        GL20.glUseProgram(shaders_fixer$program);
    }
    */
}
