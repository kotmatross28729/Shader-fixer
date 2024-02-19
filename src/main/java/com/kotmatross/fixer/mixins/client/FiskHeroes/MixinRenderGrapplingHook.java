package com.kotmatross.fixer.mixins.client.FiskHeroes;

import com.fiskmods.heroes.client.render.entity.projectile.RenderGrapplingHook;
import com.fiskmods.heroes.common.entity.projectile.AbstractEntityGrapplingHook;
import com.fiskmods.heroes.common.entity.projectile.EntitySpellWhip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.fixer.utils.shaders_fix;

@Mixin(value = RenderGrapplingHook.class, priority = 999)
public abstract class MixinRenderGrapplingHook extends Render {

    @Inject(method = "render",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(AbstractEntityGrapplingHook entity, double x, double y, double z, float f, float partialTicks, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
    }
}
