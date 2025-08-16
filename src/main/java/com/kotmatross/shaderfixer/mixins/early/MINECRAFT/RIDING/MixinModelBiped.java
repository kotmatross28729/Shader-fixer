package com.kotmatross.shaderfixer.mixins.early.MINECRAFT.RIDING;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ModelBiped.class, priority = 1009)
public class MixinModelBiped extends ModelBase {

    @ModifyConstant(method = "setRotationAngles", constant = @Constant(floatValue = -((float) Math.PI * 2F / 5F)))
    public float transformLegRotateAngleX(float angle) {
        return ((float) (-Math.PI * 9F / 18F)); // 20 in vanilla (or not, the fuck is that 1.4137167), but at 20, there
                                                // are still triangles on the very edge (so 18 (1.5707963))
    }

    @ModifyConstant(
        method = "setRotationAngles",
        constant = @Constant(floatValue = ((float) Math.PI / 10F), ordinal = 0))
    public float transformLegRotateAngleYRIGHT(float angle) {
        return ((float) Math.PI / 12);
    }

    @ModifyConstant(
        method = "setRotationAngles",
        constant = @Constant(floatValue = -((float) Math.PI / 10F), ordinal = 0))
    public float transformLegRotateAngleYLEFT(float angle) {
        return -((float) Math.PI / 12);
    }

}
