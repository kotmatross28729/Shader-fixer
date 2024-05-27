package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.model;

import assets.rivalrebels.client.model.ModelBlastRing;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = ModelBlastRing.class, priority = 999)
public class MixinModelBlastRing {
    @Inject(method = "renderModel*", at = @At(value = "HEAD"), remap = false)
    public void renderModel(float size, int segments, float thickness, float height, float pitch, float yaw, float roll, float x, float y, float z, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
