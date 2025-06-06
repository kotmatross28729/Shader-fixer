package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.armorfix;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;

@Mixin(value = ModelBiped.class, priority = 999)
public class MixinModelBiped {

    @Shadow
    public ModelRenderer bipedHead;
    @Shadow
    public ModelRenderer bipedLeftArm;

    // Allows non-player mobs to hold akimbo weapons correctly (not tilting left hand like a bow)
    // AND, fixes the rendering of akimbo weapons for players with CustomPlayerModels model
    // (CustomPlayerModels does the transformation before NTM does it in the forge event, so we do it right in the
    // vanilla code :D)

    @Inject(method = "setRotationAngles", at = @At(value = "TAIL"))
    public void setRotationAngles(float walkCycle, float walkAmplitude, float idleCycle, float headYaw, float headPitch,
        float scale, Entity entity, CallbackInfo ci) {
        if ((entity instanceof EntityLivingBase entityLivingBase)) {
            ItemStack held = entityLivingBase.getHeldItem();
            if (held != null) {
                IItemRenderer customRenderer = MinecraftForgeClient
                    .getItemRenderer(held, IItemRenderer.ItemRenderType.EQUIPPED);
                if (ShadersFixerLateMixins.handleVibe(customRenderer)) {
                    this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
                }
            }
        }
    }

}
