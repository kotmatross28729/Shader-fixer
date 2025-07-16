package com.kotmatross.shaderfixer.mixins.early.MINECRAFT.FPS_BYPASS;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@Mixin(value = Minecraft.class, priority = 1999)
public class MixinMinecraft {

    @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
    public int getLimitFramerate(int fpsLimit) {
        if (ShaderFixerConfig.VANILLA_MAIN_MENU_FPS_BYPASS_VALUE == -1
            || ShaderFixerConfig.VANILLA_MAIN_MENU_FPS_BYPASS_VALUE == 0) {
            return Minecraft.getMinecraft().gameSettings.limitFramerate;
        } else {
            return ShaderFixerConfig.VANILLA_MAIN_MENU_FPS_BYPASS_VALUE;
        }
    }
}
