package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ilya3point999k.thaumicconcilium.client.render.item.AstralMonitorRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(value = AstralMonitorRenderer.class, priority = 999)
public class MixinAstralMonitorRenderer {

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V",
            ordinal = 0,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(false);
        // }
        shaders_fixer$program.set(Utils.GLGetCurrentProgram());
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V",
            ordinal = 0,
            shift = AFTER),
        remap = false)
    private void afterUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
        Utils.GLUseProgram(shaders_fixer$program.get());
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(true);
        // }
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V",
            ordinal = 1,
            shift = BEFORE),
        remap = false)
    private void beforeUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shaders_fixer$program2") LocalIntRef shaders_fixer$program2) {
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(false);
        // }
        shaders_fixer$program2.set(Utils.GLGetCurrentProgram());
    }

    @Inject(
        method = "renderItem",
        at = @At(
            value = "INVOKE",
            target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V",
            ordinal = 1,
            shift = AFTER),
        remap = false)
    private void afterUseShader2(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci,
        @Share("shaders_fixer$program2") LocalIntRef shaders_fixer$program2) {
        Utils.GLUseProgram(shaders_fixer$program2.get());
        // if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
        // GL11.glDepthMask(true);
        // }
    }
}
