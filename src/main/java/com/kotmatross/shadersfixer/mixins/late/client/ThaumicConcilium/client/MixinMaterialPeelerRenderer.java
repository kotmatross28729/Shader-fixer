package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import com.ilya3point999k.thaumicconcilium.client.render.MaterialPeelerRenderer;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = MaterialPeelerRenderer.class, priority = 999)
public class MixinMaterialPeelerRenderer {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_76986_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader(Entity entity, double x, double y, double z, float yaw, float partialTickTime, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
    }
    @Inject(method = "func_76986_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader(Entity entity, double x, double y, double z, float yaw, float partialTickTime, CallbackInfo ci) {
        Utils.GLUseCurrentProgram(shaders_fixer$program);
    }
}
