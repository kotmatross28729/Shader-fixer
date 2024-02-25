package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.projectile;

import com.fiskmods.heroes.client.render.projectile.ProjectileRenderHandler;
import com.fiskmods.heroes.common.projectile.ProjectileTrail;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.vecmath.Point3f;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = ProjectileRenderHandler.class, priority = 999)
public abstract class MixinProjectileRenderHandler {
    @Inject(method = "renderTrail",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    private void renderTrail(ProjectileTrail trail, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
