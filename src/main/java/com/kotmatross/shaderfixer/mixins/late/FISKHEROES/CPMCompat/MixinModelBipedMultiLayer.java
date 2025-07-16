package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.CPMCompat;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.fiskmods.heroes.client.model.ModelBipedMultiLayer;

/**
 * @author Darek505
 */
@Mixin(value = ModelBipedMultiLayer.class, priority = 999)
public class MixinModelBipedMultiLayer extends ModelBiped {

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
        float headPitch, float scaleFactor, Entity entity) {
        Render render = RenderManager.instance.getEntityRenderObject(entity);
        if (render instanceof RenderPlayer renderPlayer) {
            this.shaders_fixer$copyRotations(renderPlayer.modelBipedMain);
        } else if (render instanceof RenderBiped renderBiped) {
            this.shaders_fixer$copyRotations(renderBiped.modelBipedMain);
        }
    }

    @Unique
    private void shaders_fixer$copyRotations(ModelBiped biped) {
        this.isSneak = biped.isSneak;
        this.isRiding = biped.isRiding;
        this.aimedBow = biped.aimedBow;

        shaders_fixer$copyRotationFromBiped(this.bipedHead, biped.bipedHead);
        shaders_fixer$copyRotationFromBiped(this.bipedBody, biped.bipedBody);
        shaders_fixer$copyRotationFromBiped(this.bipedRightArm, biped.bipedRightArm);
        shaders_fixer$copyRotationFromBiped(this.bipedLeftArm, biped.bipedLeftArm);
        shaders_fixer$copyRotationFromBiped(this.bipedRightLeg, biped.bipedRightLeg);
        shaders_fixer$copyRotationFromBiped(this.bipedLeftLeg, biped.bipedLeftLeg);
    }

    @Unique
    private void shaders_fixer$copyRotationFromBiped(ModelRenderer to, ModelRenderer from) {
        to.offsetX = from.offsetX;
        to.offsetY = from.offsetY;
        to.offsetZ = from.offsetZ;
        to.rotateAngleX = from.rotateAngleX;
        to.rotateAngleY = from.rotateAngleY;
        to.rotateAngleZ = from.rotateAngleZ;
        to.rotationPointX = from.rotationPointX;
        to.rotationPointY = from.rotationPointY;
        to.rotationPointZ = from.rotationPointZ;
    }
}
