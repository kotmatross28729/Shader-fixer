package com.kotmatross.shaderfixer.mixins.early.HBM.SEDNA;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;

/**
 * Main NTM gun fix
 * <p>
 * How does it do that?
 * <p>
 * I just looked at the NTM code and compared it to the vanilla code (what was added/removed)
 * <p>
 * Then I implemented all of these things into the vanilla code if the player is holding a gun
 * <p>
 * In short, instead of completely rewriting the renderer like NTM does, move gun renderer to the vanilla render
 * pipeline
 * 
 * @author kotmatross
 */

@FuckingCursed
@Mixin(value = ItemRenderer.class, priority = 1003)
public class MixinItemRenderer {

    @Shadow
    private ItemStack itemToRender;
    @Shadow
    private float prevEquippedProgress;
    @Shadow
    private float equippedProgress;
    @Unique
    EntityPlayer shader_fixer$player;
    @Unique
    float shader_fixer$swayMagnitude;
    @Unique
    float shader_fixer$swayPeriod;
    @Unique
    float shader_fixer$turnMagnitude;
    @Unique
    float shader_fixer$pitch;
    @Unique
    float shader_fixer$yaw;
    @Unique
    float shader_fixer$armPitch;
    @Unique
    float shader_fixer$armYaw;
    @Unique
    float shader_fixer$f1;
    @Unique
    Minecraft shader_fixer$mc;
    @Unique
    float shader_fixer$swing;

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "HEAD"))
    public void renderItemInFirstPerson(float interp, CallbackInfo ci) {

        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            NTMUtils_WRAPPER.handleInterpolation(interp); // INTERPOLATE AIM
            shader_fixer$swayMagnitude = NTMUtils_WRAPPER.getGunsSwayMagnitude(itemToRender);
            shader_fixer$swayPeriod = NTMUtils_WRAPPER.getGunsSwayPeriod(itemToRender);
            shader_fixer$turnMagnitude = NTMUtils_WRAPPER.getGunsTurnMagnitude(itemToRender);
        }

        shader_fixer$f1 = prevEquippedProgress + (equippedProgress - prevEquippedProgress) * interp;

        shader_fixer$mc = Minecraft.getMinecraft();
        shader_fixer$player = shader_fixer$mc.thePlayer;

        shader_fixer$pitch = shader_fixer$player.prevRotationPitch
            + (shader_fixer$player.rotationPitch - shader_fixer$player.prevRotationPitch) * interp;
        shader_fixer$yaw = shader_fixer$player.prevRotationYaw
            + (shader_fixer$player.rotationYaw - shader_fixer$player.prevRotationYaw) * interp;

        EntityPlayerSP entityplayersp = (EntityPlayerSP) shader_fixer$player;

        shader_fixer$armPitch = entityplayersp.prevRenderArmPitch
            + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * interp;
        shader_fixer$armYaw = entityplayersp.prevRenderArmYaw
            + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * interp;

        shader_fixer$swing = shader_fixer$player.getSwingProgress(interp);
    }

    // CHANGED IN NTM
    @ModifyArg(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 2),
        index = 0,
        remap = false)
    private float modifyPitchRotation(float angle) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON))
            return (shader_fixer$player.rotationPitch - shader_fixer$armPitch) * 0.1F * shader_fixer$turnMagnitude;
        return (shader_fixer$player.rotationPitch - shader_fixer$armPitch) * 0.1F;
    }

    // CHANGED IN NTM
    @ModifyArg(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 3),
        index = 0,
        remap = false)
    private float modifyYawRotation(float angle) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON))
            return (shader_fixer$player.rotationYaw - shader_fixer$armYaw) * 0.1F * shader_fixer$turnMagnitude;
        return (shader_fixer$player.rotationYaw - shader_fixer$armYaw) * 0.1F;
    }

    // TODO: Redirect -> WrapWithCondition

    // REMOVED IN NTM
    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 7),
        remap = false)
    public void skipGlTranslate(float x, float y, float z) {
        if (!NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            float f13 = 0.8F;
            GL11.glTranslatef(0.7F * f13, -0.65F * f13 - (1.0F - shader_fixer$f1) * 0.6F, -0.9F * f13);
        }
    }

    // REMOVED IN NTM
    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 18),
        remap = false)
    public void skipGlRotate(float angle, float x, float y, float z) {
        if (!NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    // REMOVED IN NTM
    @Redirect(
        method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", ordinal = 3),
        remap = false)
    public void skipGlScale(float x, float y, float z) {
        if (!NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            GL11.glScalef(0.4F, 0.4F, 0.4F);
        }
    }

    // ADDED IN NTM
    @Inject(
        method = "renderItemInFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
            shift = At.Shift.BEFORE),
        remap = false)
    private void addGlRotated(float interp, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            GL11.glRotated(180, 0, 1, 0);
        }
    }

    // ADDED IN NTM
    @Inject(
        method = "renderItemInFirstPerson",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
            shift = At.Shift.BEFORE),
        remap = false)
    private void addFinalPreRenderStuff(float interp, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
            if (shader_fixer$mc.renderViewEntity instanceof EntityPlayer entityplayer) {
                float distanceDelta = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
                float distanceInterp = -(entityplayer.distanceWalkedModified + distanceDelta * interp);
                float camYaw = entityplayer.prevCameraYaw
                    + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * interp;
                float camPitch = entityplayer.prevCameraPitch
                    + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * interp;
                GL11.glTranslatef(
                    MathHelper.sin(distanceInterp * (float) Math.PI * shader_fixer$swayPeriod) * camYaw
                        * 0.5F
                        * shader_fixer$swayMagnitude,
                    -Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * shader_fixer$swayPeriod) * camYaw)
                        * shader_fixer$swayMagnitude,
                    0.0F);
                GL11.glRotatef(
                    MathHelper.sin(distanceInterp * (float) Math.PI * shader_fixer$swayPeriod) * camYaw * 3.0F,
                    0.0F,
                    0.0F,
                    1.0F);
                GL11.glRotatef(
                    Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * shader_fixer$swayPeriod - 0.2F) * camYaw)
                        * 5.0F,
                    1.0F,
                    0.0F,
                    0.0F);
                GL11.glRotatef(camPitch, 1.0F, 0.0F, 0.0F);
            }
        }
    }
}
