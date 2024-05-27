package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import assets.rivalrebels.client.renderentity.RenderTsarBlast;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderTsarBlast.class, priority = 999)
public class MixinRenderTsarBlast {

    @Inject(method = "func_76986_a*", at = @At(value = "HEAD"), remap = false)
    private void func_76986_a(Entity var1, double x, double y, double z, float var8, float var9, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
