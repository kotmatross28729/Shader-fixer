package com.kotmatross.shadersfixer.mixins.late.client.cnpc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.entity.EntityNPCInterface;

@Mixin(value = RenderNPCInterface.class, priority = 999)
public class MixinRenderNPCInterface {

    @Inject(method = "renderLivingLabel", at = @At(value = "HEAD"), remap = false)
    public void renderLivingLabel(EntityNPCInterface npc, double d, double d1, double d2, int i, Object[] obs,
        CallbackInfo ci) {
        Utils.Fix();
    }
}
