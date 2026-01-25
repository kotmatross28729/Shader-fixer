package com.kotmatross.shaderfixer.mixins.early.ntm.armorfix;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.ShaderFixer;
import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = ModelBiped.class, priority = 999)
public class MixinModelBiped {

    @Shadow
    public ModelRenderer bipedHead;
    @Shadow
    public ModelRenderer bipedLeftArm;

    /**
     * Allows non-player mobs to hold akimbo weapons correctly (not tilting left hand like a bow)
     * AND, fixes the rendering of akimbo weapons for players with CustomPlayerModels model
     * (CustomPlayerModels does the transformation before NTM does it in the forge event, so we do it right in the
     * vanilla code :D)
     */
    @Inject(method = "setRotationAngles", at = @At(value = "TAIL"))
    public void setRotationAngles(CallbackInfo ci, @Local(argsOnly = true) Entity entity) {
        if (ShaderFixer.IS_HBM_NTM_PRESENT) {
            if ((entity instanceof EntityLivingBase entityLivingBase)) {
                if (NTMUtils_WRAPPER.checkVibe_Akimbo(entityLivingBase)) {
                    this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
                }
            }
        }
    }

}
