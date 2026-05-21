package com.kotmatross.shaderfixer.mixins.early.ntm.gunfix;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shaderfixer.utils.ntm.NTMUtils_WRAPPER;

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
@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(value = ItemRenderer.class, priority = 1003)
public class MixinItemRenderer {

    @Shadow
    public ItemStack itemToRender;
    @Shadow
    private Minecraft mc;
	@Unique
    float swayMagnitude;
    @Unique
    float swayPeriod;
    @Unique
    float turnMagnitude;
    
    @Inject(method = "renderItemInFirstPerson"
            , at = @At(value = "HEAD"))
    public void init(float interp, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe()) {
            NTMUtils_WRAPPER.handleInterpolation(interp);
            swayMagnitude = NTMUtils_WRAPPER.getGunsSwayMagnitude(itemToRender);
            swayPeriod = NTMUtils_WRAPPER.getGunsSwayPeriod(itemToRender);
            turnMagnitude = NTMUtils_WRAPPER.getGunsTurnMagnitude(itemToRender);
        }
    }
    
    /// CHANGED IN NTM
    @WrapOperation(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"
                , ordinal = 2))
    private void modifyPitchRotation(float angle, float x, float y, float z, Operation<Void> original) {
        if (NTMUtils_WRAPPER.checkVibe())
            original.call(angle * turnMagnitude, x, y, z);
        else
            original.call(angle, x, y, z);
    }
    
    /// CHANGED IN NTM
    @WrapOperation(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"
                , ordinal = 3))
    private void modifyYawRotation(float angle, float x, float y, float z, Operation<Void> original) {
        if (NTMUtils_WRAPPER.checkVibe())
            original.call(angle * turnMagnitude, x, y, z);
        else
            original.call(angle, x, y, z);
    }
    
    /// REMOVED IN NTM
    @WrapWithCondition(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"
                , remap = false
                , ordinal = 7))
    private boolean skipT(float x, float y, float z) {
        return !NTMUtils_WRAPPER.checkVibe();
    }
    
    /// REMOVED IN NTM
    @WrapWithCondition(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"
                , remap = false
                , ordinal = 18))
    private boolean skipR(float angle, float x, float y, float z) {
        return !NTMUtils_WRAPPER.checkVibe();
    }
    
    /// REMOVED IN NTM
    @WrapWithCondition(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V"
                , remap = false
                , ordinal = 3))
    private boolean skipS(float x, float y, float z) {
        return !NTMUtils_WRAPPER.checkVibe();
    }
    
    /// ADDED IN NTM
    @Inject(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V"
                , shift = At.Shift.BEFORE))
    private void addGlRotated(float interp, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe()) {
            GL11.glRotated(180, 0, 1, 0);
        }
    }

    /// ADDED IN NTM
    @Inject(method = "renderItemInFirstPerson"
            , at = @At(value = "INVOKE"
                , target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V"
                , shift = At.Shift.BEFORE))
    private void addFinalPreRenderStuff(float interp, CallbackInfo ci) {
        if (NTMUtils_WRAPPER.checkVibe()) {
            if (mc.renderViewEntity instanceof EntityPlayer entityplayer) {
                float distanceDelta = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
                float distanceInterp = -(entityplayer.distanceWalkedModified + distanceDelta * interp);
                float camYaw = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * interp;
                float camPitch = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * interp;
                GL11.glTranslatef(MathHelper.sin(distanceInterp * (float) Math.PI * swayPeriod) * camYaw * 0.5F * swayMagnitude, -Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * swayPeriod) * camYaw) * swayMagnitude, 0.0F);
                GL11.glRotatef(MathHelper.sin(distanceInterp * (float) Math.PI * swayPeriod) * camYaw * 3.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * swayPeriod - 0.2F) * camYaw) * 5.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(camPitch, 1.0F, 0.0F, 0.0F);
            }
        }
    }

}
