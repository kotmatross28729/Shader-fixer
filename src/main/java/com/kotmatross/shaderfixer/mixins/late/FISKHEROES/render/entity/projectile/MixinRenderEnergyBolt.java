package com.kotmatross.shaderfixer.mixins.late.FISKHEROES.render.entity.projectile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fiskmods.heroes.client.render.entity.projectile.RenderEnergyBolt;
import com.fiskmods.heroes.common.entity.projectile.EntityEnergyBolt;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = RenderEnergyBolt.class, priority = 999)
public class MixinRenderEnergyBolt {

    @Inject(
        method = "renderBolt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void renderBolt(EntityEnergyBolt entity, double x, double y, double z, float f, float partialTicks,
        CallbackInfo ci) {
        Utils.fix();
    }

    @ModifyArg(
        method = "renderBolt",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4d(DDDD)V"),
        index = 3,
        remap = false)
    private double alphaFix(double alpha) {
        return AngelicaUtils.isShaderEnabled() ? Math.max(alpha, 0.1) : alpha;
    }

}
