package com.kotmatross.shaderfixer.mixins.late.HBM.space;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.OrreryPronter;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;
import com.kotmatross.shaderfixer.utils.Utils;

@SPEKJORK
@Mixin(value = OrreryPronter.class, priority = 999)
public class MixinOrreryPronter {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void render(Minecraft mc, World world, float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }
}
