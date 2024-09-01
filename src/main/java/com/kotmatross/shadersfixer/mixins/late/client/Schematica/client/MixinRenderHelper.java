package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import com.github.lunatrius.core.util.vector.Vector3f;
import com.github.lunatrius.schematica.client.renderer.RenderHelper;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderHelper.class, priority = 999)
public class MixinRenderHelper {

    @Inject(method = "drawCuboidSurface", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidSurface(Vector3f zero, Vector3f size, int sides, float red, float green, float blue, float alpha, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "drawCuboidOutline", at = @At(value = "HEAD"), remap = false)
    private static void drawCuboidOutline(Vector3f zero, Vector3f size, int sides, float red, float green, float blue, float alpha, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
