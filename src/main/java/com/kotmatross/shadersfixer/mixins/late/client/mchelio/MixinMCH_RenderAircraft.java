package com.kotmatross.shadersfixer.mixins.late.client.mchelio;

import com.kotmatross.shadersfixer.Utils;
import mcheli.aircraft.MCH_AircraftInfo;
import mcheli.aircraft.MCH_BoundingBox;
import mcheli.aircraft.MCH_EntityAircraft;
import mcheli.aircraft.MCH_RenderAircraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MCH_RenderAircraft.class, priority = 999)
public class MixinMCH_RenderAircraft {

    @Inject(method = "drawHitBoxDetail",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void drawHitBoxDetail(MCH_BoundingBox bb, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderLight",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderLight(double x, double y, double z, float tickTime, MCH_EntityAircraft ac, MCH_AircraftInfo info, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderCrawlerTrack",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderCrawlerTrack(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderEntityMarker",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderEntityMarker(Entity entity, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "renderRope",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    private static void renderRope(MCH_EntityAircraft ac, MCH_AircraftInfo info, double x, double y, double z, float tickTime, CallbackInfo ci) {
        Utils.Fix();
    }
}
