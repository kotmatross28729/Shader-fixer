package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.sedna;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;
import com.llamalad7.mixinextras.sugar.Local;

@FuckingCursed
@Mixin(value = EntityRenderer.class, priority = 1003)
public class MixinEntityRenderer {
    // FOR VANILLA (NO OPTIFINE/ANGELICA)

    // Don't even ask, I don't give a fuck what that means
    @Unique
    public boolean shaders_fixer$checkVibe() {
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if (toRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED_FIRST_PERSON);
            return renderer instanceof Vibe;
        }
        return false;
    }

    @Shadow
    private double cameraZoom;
    @Shadow
    private double cameraYaw;
    @Shadow
    private double cameraPitch;

    @Inject(method = "renderHand", at = @At(value = "HEAD"))
    public void HandleInterp(float interp, int p_78476_2_, CallbackInfo ci) {
        if (shaders_fixer$checkVibe()) {
            try {
                ShadersFixerLateMixins.handleInterpolation(interp);
            } catch (NoClassDefFoundError ignored) {} // INTERPOLATE FOV (SCOPE)
        }
    }

    @Redirect(
        method = "renderHand",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 1),
        remap = false)
    public void skip1(float X, float XX, float XXX) {
        if (!shaders_fixer$checkVibe()) {
            GL11.glTranslatef((float) this.cameraYaw, (float) (-this.cameraPitch), 0.0F);
        }
    }

    @Redirect(
        method = "renderHand",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScaled(DDD)V", ordinal = 0),
        remap = false)
    public void skip2(double X, double XX, double XXX) {
        if (!shaders_fixer$checkVibe()) {
            GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
        }
    }

    @ModifyArg(
        method = "renderHand",
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

    @ModifyConstant(method = "getFOVModifier", constant = @Constant(floatValue = 70.0F))
    public float ModifyBaseFOV(float fov, @Local EntityLivingBase entityplayer) {
        if (shaders_fixer$checkVibe()) {
            fov = ShadersFixerLateMixins.getGunsBaseFOV(entityplayer.getHeldItem());
            return fov;
        }
        return fov;
    }
}
