package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.model;

import rivalrebels.client.model.ModelAstroBlasterBody;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = ModelAstroBlasterBody.class, priority = 999)
public class MixinModelAstroBlasterBody {
    @Inject(method = "render*", at = @At(value = "HEAD"), remap = false)
    public void render(float size, float red, float green, float blue, float alpha, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
