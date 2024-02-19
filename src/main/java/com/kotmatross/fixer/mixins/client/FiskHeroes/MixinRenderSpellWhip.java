package com.kotmatross.fixer.mixins.client.FiskHeroes;

import com.fiskmods.heroes.client.render.entity.projectile.RenderSpellWhip;
import com.fiskmods.heroes.common.entity.projectile.EntitySpellWhip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.fixer.utils.shaders_fix;

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
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
        Tessellator.instance.setBrightness(15728880);
    }
}
