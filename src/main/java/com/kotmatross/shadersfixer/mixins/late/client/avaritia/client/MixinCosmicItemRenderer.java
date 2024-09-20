package com.kotmatross.shadersfixer.mixins.late.client.avaritia.client;

import com.kotmatross.shadersfixer.Utils;
import fox.spiteful.avaritia.render.CosmicItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = CosmicItemRenderer.class, priority = 999)
public class MixinCosmicItemRenderer {
    @Unique
    public int shaders_fixer$program; // Делей программ

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
    }
    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader(IItemRenderer.ItemRenderType type, ItemStack item, Object[] data, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Unique
    public int shaders_fixer$program2;// Энозер делей программ
    @Unique
    public float shaders_fixer$lbx;// Ласт брайтнесс икс
    @Unique
    public float shaders_fixer$lby;// Ласт брайтнесс игрик

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;useShader()V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci) {
        shaders_fixer$program2 = Utils.GLGetCurrentProgram();
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lfox/spiteful/avaritia/render/CosmicRenderShenanigans;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader2(ItemStack item, EntityPlayer player, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program2);
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }
}
