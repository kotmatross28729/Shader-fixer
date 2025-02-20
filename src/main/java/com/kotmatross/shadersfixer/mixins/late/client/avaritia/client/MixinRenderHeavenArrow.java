package com.kotmatross.shadersfixer.mixins.late.client.avaritia.client;

import com.kotmatross.shadersfixer.Utils;
import fox.spiteful.avaritia.render.RenderHeavenArrow;
import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderHeavenArrow.class, priority = 999)
public class MixinRenderHeavenArrow {
    //!Not working with angelica
    
    @Unique
    public float shaders_fixer$lbx;
    @Unique
    public float shaders_fixer$lby;

    @Inject(method = "func_76986_a", at = @At(value = "HEAD"), remap = false)
    private void beforeUseShader2(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }

    @Inject(method = "func_76986_a", at = @At(value = "TAIL"), remap = false)
    private void afterUseShader2(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }
}
