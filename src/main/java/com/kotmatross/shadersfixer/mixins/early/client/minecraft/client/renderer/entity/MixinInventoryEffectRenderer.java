package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Worst mixin
@Mixin(value = InventoryEffectRenderer.class, priority = 999)
public class MixinInventoryEffectRenderer {
//Missing blend i think (it is it's is it)
    @Inject(method = "func_147044_g", at = @At("HEAD"))
    public void func_147044_g(CallbackInfo ci) {
        GL11.glPushMatrix();
        //GL11.glDisable(GL11.GL_ALPHA_TEST); //wh
        GL11.glEnable(GL11.GL_BLEND);
        //OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA, 0, 0); //what
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0); //The first value should be 1 somehow (p_148821_2_->sfactorAlpha, dunno what it is)
    }
    /*
    @Inject(method = "func_147044_g", at = @At("RETURN"))
    public void func_147044_g2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glPopMatrix();
    }*/

    @Inject(method = "func_147044_g", at = @At("TAIL"))
    public void func_147044_g2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
