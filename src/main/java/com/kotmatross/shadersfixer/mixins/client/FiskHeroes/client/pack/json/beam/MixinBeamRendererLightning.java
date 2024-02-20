package com.kotmatross.shadersfixer.mixins.client.FiskHeroes.client.pack.json.beam;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = com.fiskmods.heroes.client.pack.json.beam.BeamRendererLightning.class, priority = 999)
public abstract class MixinBeamRendererLightning implements com.fiskmods.heroes.client.pack.json.beam.IBeamRenderer {
    @Inject(method = "render",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(Entity anchor, float width, float height, float beamScale, Long seed, Vec3 src, Vec3 dst, Vec3 color, float opacity0, float opacity1, float scale0, float scale1, float time, float scale, boolean isClientPlayer, boolean isFirstPerson, float partialTicks, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
