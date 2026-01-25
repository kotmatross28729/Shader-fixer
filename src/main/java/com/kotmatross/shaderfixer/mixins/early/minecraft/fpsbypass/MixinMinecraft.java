package com.kotmatross.shaderfixer.mixins.early.minecraft.fpsbypass;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@Mixin(value = Minecraft.class, priority = 1999)
public class MixinMinecraft {

    @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
    public int getLimitFramerate(int fpsLimit) {
        return ShaderFixerConfig.V_MAIN_MENU_FPS_BYPASS_VALUE == 0
            ? Minecraft.getMinecraft().gameSettings.limitFramerate
            : ShaderFixerConfig.V_MAIN_MENU_FPS_BYPASS_VALUE;
    }

}
