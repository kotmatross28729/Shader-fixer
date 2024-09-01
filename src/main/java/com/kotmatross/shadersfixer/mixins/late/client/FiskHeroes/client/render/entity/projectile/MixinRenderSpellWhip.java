package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.entity.projectile;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSpellWhip;
import com.fiskmods.heroes.common.entity.projectile.EntitySpellWhip;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSpellWhip.class, priority = 999)
public abstract class MixinRenderSpellWhip extends Render {
    @Inject(method = "doRender",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void doRender(EntitySpellWhip entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
        Tessellator.instance.setBrightness(15728880);

    }
}
