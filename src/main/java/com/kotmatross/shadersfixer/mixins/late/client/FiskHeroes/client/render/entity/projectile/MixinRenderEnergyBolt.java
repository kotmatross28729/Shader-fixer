package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;


import com.fiskmods.heroes.client.render.entity.projectile.RenderEnergyBolt;
import com.fiskmods.heroes.common.entity.projectile.EntityEnergyBolt;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderEnergyBolt.class, priority = 999)
public class MixinRenderEnergyBolt {

    @Inject(method = "renderBolt",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderBolt(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
