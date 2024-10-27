package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = Minecraft.class, priority = 1999)
public class MixinMinecraft {
    @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
    public int getLimitFramerate(int fpsLimit)
    {
        if(ShaderFixerConfig.MainMenuFPSValue == -1 || ShaderFixerConfig.MainMenuFPSValue == 0) {
            return Minecraft.getMinecraft().gameSettings.limitFramerate;
        } else {
            return ShaderFixerConfig.MainMenuFPSValue;
        }
    }
}
