package com.kotmatross.shaderfixer.mixins.early.minecraft.fixxpbaralpha;

import net.minecraftforge.client.GuiIngameForge;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiIngameForge.class, priority = 1009)
public class MixinGuiIngameForge {

    @Inject(method = "renderExperience", at = @At("HEAD"), remap = false)
    private void enableAlphaTest(CallbackInfo ci) {
        GL11.glEnable(GL11.GL_ALPHA_TEST); // Peak mjoang coding v3
    }

}
