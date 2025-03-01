package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Render.class, priority = 999)
public class MixinRender {

    //Nametag rendering on player (and mobs)
    
    @Inject(method = "func_147906_a", at = @At(value = "HEAD"))
    protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "func_147906_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", ordinal = 1, shift = At.Shift.AFTER))
    private void func_147906_a2(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_, CallbackInfo ci) {
        GL11.glDepthMask(false);
    }

    @Inject(method = "func_147906_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", shift = At.Shift.AFTER))
    private void func_147906_a3(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_, CallbackInfo ci) {
        GL11.glDepthMask(true);
    }
}
