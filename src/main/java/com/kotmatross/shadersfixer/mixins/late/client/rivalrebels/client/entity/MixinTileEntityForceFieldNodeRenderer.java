package com.kotmatross.shadersfixer.mixins.late.client.rivalrebels.client.entity;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import rivalrebels.common.entity.EntityRhodes;
import rivalrebels.common.tileentity.TileEntityForceFieldNode;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = TileEntityForceFieldNodeRenderer.class, priority = 999)
public class MixinTileEntityForceFieldNodeRenderer {

    @Inject(method = "renderAModelAt",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 1)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void renderAModelAt(TileEntityForceFieldNode tile, double x, double y, double z, float f, CallbackInfo ci)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
