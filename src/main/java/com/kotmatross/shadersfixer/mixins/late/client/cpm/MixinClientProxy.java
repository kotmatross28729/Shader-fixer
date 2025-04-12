package com.kotmatross.shadersfixer.mixins.late.client.cpm;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;
import com.tom.cpm.client.ClientProxy;

@Mixin(value = ClientProxy.class, priority = 999)
public class MixinClientProxy {

    @Inject(
        method = "onRenderName",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            shift = At.Shift.BEFORE))
    public void onRenderName(RenderLivingEvent.Specials.Pre evt, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderLivingLabel0", at = @At(value = "HEAD"), remap = false)
    public void renderLivingLabel0(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_,
        double p_147906_7_, int p_147906_9_, CallbackInfo ci) {
        Utils.Fix();
    }

}
