package com.kotmatross.shaderfixer.mixins.late.avaritia;

import net.minecraft.client.model.ModelBiped;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fox.spiteful.avaritia.render.ModelArmorInfinity;

@Mixin(value = ModelArmorInfinity.class, priority = 999)
public class MixinModelArmorInfinity extends ModelBiped {

    @Shadow(remap = false)
    public boolean legs = false;

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lfox/spiteful/avaritia/render/ModelArmorInfinity;setWings()V",
            shift = At.Shift.BEFORE),
        cancellable = true,
        remap = false)
    private void render(CallbackInfo ci) {
        if (this.legs) {
            ci.cancel(); // If wings are rendered with `legs == true`, then it will fuck up lighting on entities
                         // (somehow)
        }
    }

}
