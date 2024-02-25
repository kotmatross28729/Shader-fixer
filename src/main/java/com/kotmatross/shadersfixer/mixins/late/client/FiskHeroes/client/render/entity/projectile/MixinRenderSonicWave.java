package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSonicWave;
import com.fiskmods.heroes.common.entity.projectile.EntitySonicWave;
import com.fiskmods.heroes.util.SHRenderHelper;
import com.fiskmods.heroes.util.Vectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RenderSonicWave.class, priority = 999)
public abstract class MixinRenderSonicWave extends Render {

    @Inject(method = "render",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(EntitySonicWave entity, double x, double y, double z, float f, float partialTicks, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
