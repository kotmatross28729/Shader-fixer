package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.sedna;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;
import com.kotmatross.shadersfixer.shrimp.Vibe;

@Mixin(value = ItemRenderer.class, priority = 1003)
public class MixinItemRenderer {

    @Shadow
    private ItemStack itemToRender;
    @Shadow
    private float prevEquippedProgress;
    @Shadow
    private float equippedProgress;
    @Unique
    EntityPlayer shaders_fixer$player;
    @Unique
    float shaders_fixer$swayMagnitude;
    @Unique
    float shaders_fixer$swayPeriod;
    @Unique
    float shaders_fixer$turnMagnitude;
    @Unique
    float shaders_fixer$pitch;
    @Unique
    float shaders_fixer$yaw;
    @Unique
    float shaders_fixer$armPitch;
    @Unique
    float shaders_fixer$armYaw;
    @Unique
    float shaders_fixer$f1;
    @Unique
    Minecraft shaders_fixer$mc;
    @Unique
    float shaders_fixer$swing;

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "HEAD"))
    public void renderItemInFirstPerson(float interp, CallbackInfo ci) {

        try {
            ShadersFixerLateMixins.handleInterpolation(interp);
        } catch (NoClassDefFoundError ignored) {} // INTERPOLATE AIM

        // GETTERS
        shaders_fixer$f1 = prevEquippedProgress + (equippedProgress - prevEquippedProgress) * interp;

        ItemStack stack = itemToRender;
        shaders_fixer$mc = Minecraft.getMinecraft();
        shaders_fixer$player = shaders_fixer$mc.thePlayer;

        shaders_fixer$swayMagnitude = ShadersFixerLateMixins.getGunsSwayMagnitude(stack);
        shaders_fixer$swayPeriod = ShadersFixerLateMixins.getGunsSwayPeriod(stack);

        shaders_fixer$turnMagnitude = ShadersFixerLateMixins.getGunsTurnMagnitude(stack);
        shaders_fixer$pitch = shaders_fixer$player.prevRotationPitch
            + (shaders_fixer$player.rotationPitch - shaders_fixer$player.prevRotationPitch) * interp;
        shaders_fixer$yaw = shaders_fixer$player.prevRotationYaw
            + (shaders_fixer$player.rotationYaw - shaders_fixer$player.prevRotationYaw) * interp;

        EntityPlayerSP entityplayersp = (EntityPlayerSP) shaders_fixer$player;

        shaders_fixer$armPitch = entityplayersp.prevRenderArmPitch
            + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * interp;
        shaders_fixer$armYaw = entityplayersp.prevRenderArmYaw
            + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * interp;

        shaders_fixer$swing = shaders_fixer$player.getSwingProgress(interp);
    }

    // Don't even ask, I don't give a fuck what that means
    @Unique
    public boolean shaders_fixer$checkVibe() {
        if (itemToRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(itemToRender, EQUIPPED_FIRST_PERSON);
            return renderer instanceof Vibe;
        }
        return false;
    }

    @ModifyArg(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 2),
        index = 0)
    private float modifyPitchRotation(float angle) {
        if (shaders_fixer$checkVibe())
            return (shaders_fixer$player.rotationPitch - shaders_fixer$armPitch) * 0.1F * shaders_fixer$turnMagnitude;
        return (shaders_fixer$player.rotationPitch - shaders_fixer$armPitch) * 0.1F;
    }

    @ModifyArg(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 3),
        index = 0)
    private float modifyYawRotation(float angle) {
        if (shaders_fixer$checkVibe())
            return (shaders_fixer$player.rotationYaw - shaders_fixer$armYaw) * 0.1F * shaders_fixer$turnMagnitude;
        return (shaders_fixer$player.rotationYaw - shaders_fixer$armYaw) * 0.1F;
    }

    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 7))
    public void skipET(float x, float y, float z) {
        if (!shaders_fixer$checkVibe()) {
            float f13 = 0.8F;
            GL11.glTranslatef(0.7F * f13, -0.65F * f13 - (1.0F - shaders_fixer$f1) * 0.6F, -0.9F * f13);
        }
    }

    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 18))
    public void skipER(float angle, float x, float y, float z) {
        if (!shaders_fixer$checkVibe()) {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", ordinal = 3))
    public void skipES(float x, float y, float z) {
        if (!shaders_fixer$checkVibe()) {
            GL11.glScalef(0.4F, 0.4F, 0.4F);
        }
    }

    @Inject(
        method = "renderItemInFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
            shift = At.Shift.BEFORE))
    private void renderItemInFirstPersonGAMMA(float interp, CallbackInfo ci) {
        if (shaders_fixer$checkVibe()) {
            GL11.glRotated(180, 0, 1, 0);
        }
    }

    @Inject(
        method = "renderItemInFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
            shift = At.Shift.BEFORE))
    private void renderItemInFirstPersonGAMMAZ(float interp, CallbackInfo ci) {
        if (shaders_fixer$checkVibe()) {
            if (shaders_fixer$mc.renderViewEntity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) shaders_fixer$mc.renderViewEntity;
                float distanceDelta = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
                float distanceInterp = -(entityplayer.distanceWalkedModified + distanceDelta * interp);
                float camYaw = entityplayer.prevCameraYaw
                    + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * interp;
                float camPitch = entityplayer.prevCameraPitch
                    + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * interp;
                GL11.glTranslatef(
                    MathHelper.sin(distanceInterp * (float) Math.PI * shaders_fixer$swayPeriod) * camYaw
                        * 0.5F
                        * shaders_fixer$swayMagnitude,
                    -Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * shaders_fixer$swayPeriod) * camYaw)
                        * shaders_fixer$swayMagnitude,
                    0.0F);
                GL11.glRotatef(
                    MathHelper.sin(distanceInterp * (float) Math.PI * shaders_fixer$swayPeriod) * camYaw * 3.0F,
                    0.0F,
                    0.0F,
                    1.0F);
                GL11.glRotatef(
                    Math.abs(
                        MathHelper.cos(distanceInterp * (float) Math.PI * shaders_fixer$swayPeriod - 0.2F) * camYaw)
                        * 5.0F,
                    1.0F,
                    0.0F,
                    0.0F);
                GL11.glRotatef(camPitch, 1.0F, 0.0F, 0.0F);
            }
        }
    }

}
