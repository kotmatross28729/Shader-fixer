package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.pack.json.beam;

import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = com.fiskmods.heroes.client.pack.json.beam.BeamRendererLine.class, priority = 999)
public abstract class MixinBeamRendererLine implements com.fiskmods.heroes.client.pack.json.beam.IBeamRenderer {
    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 0))
    public void render(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst, Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale, boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V", ordinal = 1))
    public void render2(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst, Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale, boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
