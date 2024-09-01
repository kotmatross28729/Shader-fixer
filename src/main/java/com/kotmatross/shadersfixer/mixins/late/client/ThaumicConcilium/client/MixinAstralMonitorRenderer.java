package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import com.ilya3point999k.thaumicconcilium.client.render.item.AstralMonitorRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = AstralMonitorRenderer.class, priority = 999)
public class MixinAstralMonitorRenderer {

    /**
    @Unique
    public int shaders_fixer$programTEST0;

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/ARBShaderObjects;glGetUniformLocationARB(ILjava/lang/CharSequence;)I", ordinal = 0, shift = BEFORE), remap = false)
    private void test0(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        shaders_fixer$programTEST0 = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/ARBShaderObjects;glUniform1ARB(ILjava/nio/IntBuffer;)V", ordinal = 1, shift = AFTER), remap = false)
    private void test00(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        GL20.glUseProgram(shaders_fixer$programTEST0);
    }
    @Unique
    public int shaders_fixer$program00;

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/ARBShaderObjects;glGetUniformLocationARB(ILjava/lang/CharSequence;)I", ordinal = 2, shift = BEFORE), remap = false)
    private void test000(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        shaders_fixer$program00 = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }
    */


    @Unique
    public int shaders_fixer$program;

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        shaders_fixer$program = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        GL20.glUseProgram(shaders_fixer$program);
    }

    @Unique
    public int shaders_fixer$program2;

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V", ordinal = 1, shift = BEFORE), remap = false)
    private void beforeUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        shaders_fixer$program2 = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
    }

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V", ordinal = 1, shift = AFTER), remap = false)
    private void afterUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        GL20.glUseProgram(shaders_fixer$program2);
    }
}
