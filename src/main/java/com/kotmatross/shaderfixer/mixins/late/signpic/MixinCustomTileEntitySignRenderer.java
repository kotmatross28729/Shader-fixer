package com.kotmatross.shaderfixer.mixins.late.signpic;

import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntitySign;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kamesuta.mc.signpic.render.CustomTileEntitySignRenderer;
import com.kotmatross.shaderfixer.utils.AngelicaUtils_WRAPPER;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Mixin(value = CustomTileEntitySignRenderer.class, priority = 999, remap = false)
public class MixinCustomTileEntitySignRenderer extends TileEntitySignRenderer {

    @WrapMethod(method = "renderSignPictureBase")
    private void dontCastShadow(TileEntitySign tile, double x, double y, double z, float partialTicks, float opacity,
        Operation<Void> original) {
        if (!AngelicaUtils_WRAPPER.isShadowPass()) {
            original.call(tile, x, y, z, partialTicks, opacity);
        }
    }

    @Inject(method = "renderSignPictureBase"
            , at = @At(value = "HEAD"))
    private void renderSignPictureBase(CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT | GL11.GL_ENABLE_BIT | GL11.GL_MAP2_INDEX);
    }

    @Inject(method = "renderSignPictureBase"
            , at = @At(value = "TAIL"))
    private void renderSignPictureBase2(CallbackInfo ci) {
        GL11.glPopAttrib();
    }

    @WrapOperation(method = "renderSignPictureBase"
            , at = @At(value = "INVOKE"
                , target = "Lnet/minecraft/client/renderer/tileentity/TileEntitySignRenderer;renderTileEntityAt(Lnet/minecraft/tileentity/TileEntitySign;DDDF)V"
                , remap = true))
    private void wrapRenderTileEntityAt(CustomTileEntitySignRenderer instance, TileEntitySign tile, double x, double y,
        double z, float interp, Operation<Void> original) {
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT | GL11.GL_ENABLE_BIT | GL11.GL_MAP2_INDEX);
        original.call(instance, tile, x, y, z, interp);
        GL11.glPopAttrib();
    }

}
