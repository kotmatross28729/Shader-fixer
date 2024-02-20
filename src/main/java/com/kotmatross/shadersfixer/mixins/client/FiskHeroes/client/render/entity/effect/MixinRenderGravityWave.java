package com.kotmatross.shadersfixer.mixins.client.FiskHeroes.client.render.entity.effect;

import com.fiskmods.heroes.client.render.entity.effect.RenderGravityWave;
import com.fiskmods.heroes.common.entity.effect.EntityGravityWave;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderGravityWave.class, priority = 999)
public abstract class MixinRenderGravityWave extends Render {
    @Inject(method = "doRender",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void doRender(EntityGravityWave entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
