package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModelBiped.class, priority = 999)
public class MixinModelBiped2 extends ModelBase {

    @Shadow
    public ModelRenderer bipedRightLeg;
    @Shadow
    public ModelRenderer bipedLeftLeg;

    @ModifyConstant(method = "setRotationAngles", constant = @Constant(floatValue = -((float) Math.PI * 2F / 5F)))
    public float transformLegRotateAngleX(float angle) {
        return ((float) (-Math.PI * 9F / 18F)); // 20 in vanilla (or not, the fuck is that 1.4137167), but at 20, there
                                                // are still triangles on the very edge (so 18 (1.5707963))
    }

    @ModifyConstant(method = "setRotationAngles", constant = @Constant(floatValue = ((float) Math.PI / 10F)))
    public float transformLegRotateAngleYRIGHT(float angle) {
        return ((float) Math.PI / 12);
    }

    @ModifyConstant(method = "setRotationAngles", constant = @Constant(floatValue = -((float) Math.PI / 10F)))
    public float transformLegRotateAngleYLEFT(float angle) {
        return -((float) Math.PI / 12);
    }

    @Inject(method = "setRotationAngles", at = @At(value = "TAIL"))
    public void setRotationAngles(float walkCycle, float walkAmplitude, float idleCycle, float headYaw, float headPitch,
        float scale, Entity entity, CallbackInfo ci) {
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;
        if (this.isRiding) {
            this.bipedRightLeg.rotateAngleZ = ((float) Math.PI / 40F);
            this.bipedLeftLeg.rotateAngleZ = -((float) Math.PI / 40F);
        }
    }

    // (Peak mjoang coding)^2, THE FUCK IS "isPassenger"??? All my homies use "isRiding"

    // if (p_364094_.isPassenger) {
    // this.rightLeg.xRot = (float) (-Math.PI * 9F / 20F);
    // this.rightLeg.yRot = (float) (Math.PI / 10);
    // this.rightLeg.zRot = (float) (Math.PI / 40F);
    // this.leftLeg.xRot = (float) (-Math.PI * 9F / 20F);
    // this.leftLeg.yRot = (float) (-Math.PI / 10);
    // this.leftLeg.zRot = (float) (-Math.PI / 40F);
    // }

    // if (p_364094_.isPassenger) {
    // this.rightLeg.xRot = -1.4137167F;
    // this.rightLeg.yRot = Math.PI / 10;
    // this.rightLeg.zRot = 0.07853982F;
    // this.leftLeg.xRot = -1.4137167F;
    // this.leftLeg.yRot = -Math.PI / 10;
    // this.leftLeg.zRot = -0.07853982F;
    // }
}
