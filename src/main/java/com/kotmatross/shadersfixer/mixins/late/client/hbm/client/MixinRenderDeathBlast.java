package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.entity.effect.RenderDeathBlast;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderDeathBlast.class, priority = 999)
public class MixinRenderDeathBlast {

    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_76986_a", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    private void func_76986_aPR(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }
    @Inject(method = "func_76986_a", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 0, shift = At.Shift.AFTER), remap = false)
    private void func_76986_aPRE(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }


    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }
    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.Fix();
    }
}
