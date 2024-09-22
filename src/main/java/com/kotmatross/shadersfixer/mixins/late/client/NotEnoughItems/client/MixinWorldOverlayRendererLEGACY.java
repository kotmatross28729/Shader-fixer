package com.kotmatross.shadersfixer.mixins.late.client.NotEnoughItems.client;

import codechicken.nei.WorldOverlayRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.shrimp.Fucked;
import com.kotmatross.shadersfixer.shrimp.FuckingCursed;
import com.kotmatross.shadersfixer.shrimp.FuckingShit;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Fucked @FuckingCursed @FuckingShit
@Mixin(value = WorldOverlayRenderer.class, priority = 999)
public class MixinWorldOverlayRendererLEGACY {
//Prosto pizdec kakoi-to
    @Inject(method = "renderMobSpawnOverlay",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glVertex3d(DDD)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glVertex3d(DDD)V",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glVertex3d(DDD)V"), remap = false)
    private static void renderMobSpawnOverlay(Entity entity, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderChunkBounds",
        slice = @Slice(from = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V",
            ordinal = 0),
            to = @At(value = "INVOKE",
                target = "Lorg/lwjgl/opengl/GL11;glEnd()V",
                ordinal = 0)),
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V"), remap = false)
    private static void renderChunkBounds(Entity entity, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
