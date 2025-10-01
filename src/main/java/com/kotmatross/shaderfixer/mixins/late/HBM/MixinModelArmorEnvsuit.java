package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.model.ModelArmorEnvsuit;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ModelArmorEnvsuit.class, priority = 999)
public class MixinModelArmorEnvsuit {

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
        CallbackInfo ci) {
        Utils.fix();
    }
}
