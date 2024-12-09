package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.particle.ParticleDebugLine;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ParticleDebugLine.class, priority = 999)
public class MixinParticleDebugLine {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_70539_a",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 0, shift = At.Shift.BEFORE))
    public void func_70539_aPR(Tessellator tess, float interp, float x, float y, float z, float tx, float tz, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "func_70539_a",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 0, shift = At.Shift.AFTER))
    public void func_70539_aPRE(Tessellator tess, float interp, float x, float y, float z, float tx, float tz, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "func_70539_a",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void func_70539_a(Tessellator tess, float interp, float x, float y, float z, float tx, float tz, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
