package com.kotmatross.shaderfixer.mixins.late.ntm.space;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.hbm.dim.SkyProviderCelestial;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;

@SPEKJORK
@Mixin(value = SkyProviderCelestial.class, priority = 999)
public class MixinSkyProviderCelestial {

    @Inject(method = "renderDigamma", at = @At(value = "HEAD"), remap = false)
    protected void lodeStarFixBrightness(float partialTicks, WorldClient world, Minecraft mc, float celestialAngle,
        CallbackInfo ci) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @ModifyArgs(
        method = "renderDigamma",
        at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glColor4f(FFFF)V"),
        remap = false)
    private void digammaStarFixBrightness(Args args) {
        args.set(0, 1.0F);
        args.set(1, 1.0F);
        args.set(2, 1.0F);
        args.set(3, 1.0F);
    }

}
