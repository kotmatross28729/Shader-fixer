package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.entity.effect.RenderDeathBlast;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderDeathBlast.class, priority = 999)
public class MixinRenderDeathBlast {

    @ModifyArg(
        method = "renderOrb",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V"),
        index = 3,
        remap = false)
    private double alphaFix(double alpha) {
        return alpha * 5F;
    }

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.enableFullBrightness();
        Utils.fix();
    }

    @Inject(method = "func_76986_a", at = @At(value = "TAIL"), remap = false)
    public void func_76986_a2(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.BrightnessUtils.disableFullBrightness();
    }

    @Inject(method = "renderOrb", at = @At(value = "HEAD"), remap = false)
    public void renderOrb(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_,
        CallbackInfo ci) {
        Utils.fix();
    }
}
