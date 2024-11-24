package com.kotmatross.shadersfixer.mixins.late.client.HEE;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.render.entity.RenderBossDragon;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderBossDragon.class, priority = 999)
public class MixinRenderBossDragon {
    @Inject(method = "renderDragonDying",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    protected void renderDragonDying(EntityBossDragon dragon, float partialTickTime, CallbackInfo ci )
    {
        Utils.Fix();
        Utils.EnableFullBrightness();
    }
}
