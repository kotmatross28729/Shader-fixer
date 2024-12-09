package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSonicWave;
import com.fiskmods.heroes.common.entity.projectile.EntitySonicWave;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSonicWave.class, priority = 999)
public abstract class MixinRenderSonicWave extends Render {

    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(EntitySonicWave entity, double x, double y, double z, float f, float partialTicks, CallbackInfo ci) {
        Utils.Fix();
    }
}
