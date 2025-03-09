package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ilya3point999k.thaumicconcilium.client.render.item.AstralMonitorRenderer;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = AstralMonitorRenderer.class, priority = 999)
public class MixinAstralMonitorRenderer {

    @Unique
    public int shaders_fixer$program;

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(false);
        // }
        shaders_fixer$program = Utils.GLGetCurrentProgram();
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(true);
        // }
    }

    @Unique
    public int shaders_fixer$program2;

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V",
            ordinal = 1,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(false);
        // }
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V",
            ordinal = 1,
            shift = AFTER),
        remap = false)
    private void afterUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(true);
        // }
    }
}
