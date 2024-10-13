package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.sedna;

import com.kotmatross.shadersfixer.shrimp.Vibe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

@Mixin(value = ItemRenderer.class, priority = 1003)
public class MixinItemRenderer {
    @Shadow private ItemStack itemToRender;
    @Shadow private float prevEquippedProgress;
    @Shadow private float equippedProgress;
    @Unique EntityPlayer shaders_fixer$player;
    @Unique float shaders_fixer$swayMagnitude;
    @Unique float shaders_fixer$swayPeriod;
    @Unique float shaders_fixer$turnMagnitude;
    @Unique float shaders_fixer$pitch;
    @Unique float shaders_fixer$yaw;
    @Unique float shaders_fixer$armPitch;
    @Unique float shaders_fixer$armYaw;
    @Unique float shaders_fixer$f1;
    @Unique Minecraft shaders_fixer$mc;
    @Unique float shaders_fixer$swing;


    //TODO make this not shit
    @Unique
    protected float shaders_fixer$getSwayMagnitude(ItemStack stack) {
        //idk if this even changes?
        return 0.5F;
    }
    @Unique
    protected float shaders_fixer$getSwayPeriod(ItemStack stack) {
        //idk if this even changes?
        return 0.75F;
    }
    @Unique
    protected float shaders_fixer$getTurnMagnitude(ItemStack stack) {
        //TODO find a way to check for modded items when IT'S FUCKING EARLY MIXINS
//        if(stack.getItem() == ModItems.gun_am180) return ItemGunBaseNT.getIsAiming(stack) ? 2.5F : -0.5F;
//        if(stack.getItem() == ModItems.gun_light_revolver) return ItemGunBaseNT.getIsAiming(stack) ? 2.5F : -0.25F;
//        if(stack.getItem() == ModItems.gun_carbine) return ItemGunBaseNT.getIsAiming(stack) ? 2.5F : -0.5F;
//        if(stack.getItem() == ModItems.gun_congolake) return ItemGunBaseNT.getIsAiming(stack) ? 2.5F : -0.25F;
//        if(stack.getItem() == ModItems.gun_light_revolver_dani) return ItemGunBaseNT.getIsAiming(stack) ? 2.5F : -0.25F;
        return 2.75F;
    }

    @Inject(method = "renderItemInFirstPerson",
        at = @At(value = "HEAD"), remap = false)
    public void renderItemInFirstPerson(float interp, CallbackInfo ci) {
        //GETTERS
        shaders_fixer$f1 = prevEquippedProgress + (equippedProgress - prevEquippedProgress) * interp;

        ItemStack stack = itemToRender;
        shaders_fixer$mc = Minecraft.getMinecraft();
        shaders_fixer$player = shaders_fixer$mc.thePlayer;

        shaders_fixer$swayMagnitude = shaders_fixer$getSwayMagnitude(stack);
        shaders_fixer$swayPeriod = shaders_fixer$getSwayPeriod(stack);
        shaders_fixer$turnMagnitude = shaders_fixer$getTurnMagnitude(stack);
        shaders_fixer$pitch = shaders_fixer$player.prevRotationPitch + (shaders_fixer$player.rotationPitch - shaders_fixer$player.prevRotationPitch) * interp;
        shaders_fixer$yaw = shaders_fixer$player.prevRotationYaw + (shaders_fixer$player.rotationYaw - shaders_fixer$player.prevRotationYaw) * interp;

        EntityPlayerSP entityplayersp = (EntityPlayerSP) shaders_fixer$player;

        shaders_fixer$armPitch = entityplayersp.prevRenderArmPitch + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * interp;
        shaders_fixer$armYaw = entityplayersp.prevRenderArmYaw + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * interp;

        shaders_fixer$swing = shaders_fixer$player.getSwingProgress(interp);
    }

    //Don't even ask, I don't give a fuck what that means
    @Unique
    public boolean shaders_fixer$checkVibe() {
        if(itemToRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(itemToRender, EQUIPPED_FIRST_PERSON);
            return renderer instanceof Vibe;
        }
        return false;
    }

    @ModifyArg(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 2), index = 0, remap = false)
    private float modifyPitchRotation(float angle) {
        //TODO alpha
        if(shaders_fixer$checkVibe())
            return (shaders_fixer$player.rotationPitch - shaders_fixer$armPitch) * 0.1F * shaders_fixer$turnMagnitude;
        return (shaders_fixer$player.rotationPitch - shaders_fixer$armPitch) * 0.1F;
    }

    @ModifyArg(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 3), index = 0, remap = false)
    private float modifyYawRotation(float angle) {
        //TODO alpha
        if(shaders_fixer$checkVibe())
            return (shaders_fixer$player.rotationYaw - shaders_fixer$armYaw) * 0.1F * shaders_fixer$turnMagnitude;
        return (shaders_fixer$player.rotationYaw - shaders_fixer$armYaw) * 0.1F;
    }

    @Redirect(method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 7), remap = false)
    public void skipET(float x, float y, float z) {
        //TODO SKIP E
        if(!shaders_fixer$checkVibe()) {
            float f13 = 0.8F;
            GL11.glTranslatef(0.7F * f13, -0.65F * f13 - (1.0F - shaders_fixer$f1) * 0.6F, -0.9F * f13);
        }
    }

    @Redirect(method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 18), remap = false)
    public void skipER(float angle, float x, float y, float z) {
        //TODO SKIP E
        if(!shaders_fixer$checkVibe()) {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @Redirect(method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", ordinal = 3),
        require = 1, remap = false)
    public void skipES(float x, float y, float z) {
        //TODO SKIP Extra
        if(!shaders_fixer$checkVibe()) {
            GL11.glScalef(0.4F, 0.4F, 0.4F);
        }
    }

    @Inject(method = "renderItemInFirstPerson",
        at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
        shift = At.Shift.BEFORE), remap = false
    )
    private void renderItemInFirstPersonGAMMA(float interp, CallbackInfo ci) {
        if(shaders_fixer$checkVibe()) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }
    }

//    @Inject(method = "renderItemInFirstPerson",
//        at = @At(value = "INVOKE",
//            target =
//                //"Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType)V",
//                  "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
//            shift = At.Shift.BEFORE)
//    )
//    private void renderItemInFirstPersonGAMMAZ(float interp, CallbackInfo ci) {
//        if(checkVibe()) {
//            if (mc.renderViewEntity instanceof EntityPlayer) {
//                EntityPlayer entityplayer = (EntityPlayer) mc.renderViewEntity;
//                float distanceDelta = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
//                float distanceInterp = -(entityplayer.distanceWalkedModified + distanceDelta * interp);
//                float camYaw = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * interp;
//                float camPitch = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * interp;
//                GL11.glTranslatef(MathHelper.sin(distanceInterp * (float) Math.PI * swayPeriod) * camYaw * 0.5F * swayMagnitude, -Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * swayPeriod) * camYaw) * swayMagnitude, 0.0F);
//                GL11.glRotatef(MathHelper.sin(distanceInterp * (float) Math.PI * swayPeriod) * camYaw * 3.0F, 0.0F, 0.0F, 1.0F);
//                GL11.glRotatef(Math.abs(MathHelper.cos(distanceInterp * (float) Math.PI * swayPeriod - 0.2F) * camYaw) * 5.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glRotatef(camPitch, 1.0F, 0.0F, 0.0F);
//            }
//        }
//    }



}
