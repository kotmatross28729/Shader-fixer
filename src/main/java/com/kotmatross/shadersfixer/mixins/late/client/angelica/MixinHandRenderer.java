package com.kotmatross.shadersfixer.mixins.late.client.angelica;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnewhorizons.angelica.compat.mojang.Camera;
import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;
import com.kotmatross.shadersfixer.shrimp.Vibe;

@Mixin(value = HandRenderer.class, priority = 999)
public class MixinHandRenderer {

    @Unique
    public boolean shaders_fixer$checkVibe() {
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if (toRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED_FIRST_PERSON);
            return renderer instanceof Vibe;
        }
        return false;
    }

    @Inject(method = "setupGlState", at = @At(value = "HEAD"), remap = false)
    public void HandleInterp(RenderGlobal gameRenderer, Camera camera, float tickDelta, CallbackInfo ci) {
        if (shaders_fixer$checkVibe()) {
            try {
                ShadersFixerLateMixins.handleInterpolation(tickDelta);
            } catch (NoClassDefFoundError ignored) {} // INTERPOLATE FOV (SCOPE)
        }
    }

    @ModifyArg(
        method = "setupGlState",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/EntityRenderer;getFOVModifier(FZ)F",
            ordinal = 0),
        index = 1)
    private boolean FOVConfigApply(boolean useFOVSetting) {
        if (shaders_fixer$checkVibe()) {
            return ShadersFixerLateMixins.getFOVConf();
        }
        return false;
    }

}
