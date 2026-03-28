package com.kotmatross.shaderfixer.mixins.late.ntm;

import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.hbm.main.ModEventHandlerClient;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Mixin(value = ModEventHandlerClient.class, priority = 999)
public class MixinModEventHandlerClient {

    @WrapOperation(
        method = "onRenderWorldLastEvent",
        at = @At(value = "INVOKE", target = "Lcom/hbm/render/util/RenderOverhead;renderThermalSight(F)V"),
        remap = false)
    private void dontCastShadowSights(float partialTicks, Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(partialTicks);
        }
    }

    @WrapMethod(method = "preRenderEvent(Lnet/minecraftforge/client/event/RenderLivingEvent$Pre;)V", remap = false)
    private void dontCastShadowTag(RenderLivingEvent.Pre event, Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(event);
        }
    }

    @WrapMethod(method = "renderFrame", remap = false)
    private void dontCastShadowFrame(RenderItemInFrameEvent event, Operation<Void> original) {
        if (!AngelicaUtils.isShadowPass()) {
            original.call(event);
        }
    }

}
