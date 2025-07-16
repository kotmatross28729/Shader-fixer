package com.kotmatross.shaderfixer.mixins.late.HBM;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.model.ModelNo9;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = ModelNo9.class, priority = 999)
public class MixinModelNo9 {

    @Inject(
        method = "func_78088_a",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/render/loader/ModelRendererObj;render(F)V",
            ordinal = 2,
            shift = At.Shift.BEFORE),
        remap = false)
    private void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
        CallbackInfo ci) {
        Utils.Fix();
    }

}
