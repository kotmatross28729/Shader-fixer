package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import com.kotmatross.shadersfixer.shrimp.FuckingShit;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@FuckingShit
@Mixin(value = InventoryEffectRenderer.class, priority = 999)
public class MixinInventoryEffectRenderer {
    @Inject(method = "func_147044_g", at = @At("HEAD"))
    public void func_147044_g(CallbackInfo ci) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    }
    @Inject(method = "func_147044_g", at = @At("TAIL"))
    public void func_147044_g2(CallbackInfo ci) {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
