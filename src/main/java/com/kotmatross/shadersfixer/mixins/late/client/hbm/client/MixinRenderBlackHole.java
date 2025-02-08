package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.entity.effect.RenderBlackHole;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderBlackHole.class, priority = 999)
public class MixinRenderBlackHole {
    @Unique
    private static float shaders_fixer$lbx;
    @Unique
    private static float shaders_fixer$lby;
    
    @Unique
    private static float shaders_fixer$lbx2;
    @Unique
    private static float shaders_fixer$lby2;
    
    @Inject(method = "renderDisc",
            at = @At(value = "HEAD"), remap = false)
    public void renderDisc(Entity entity, float interp, CallbackInfo ci) {
        shaders_fixer$lbx = Utils.GetLastBrightnessX();
        shaders_fixer$lby = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }
    @Inject(method = "renderDisc",
            at = @At(value = "TAIL"), remap = false)
    public void renderDisc2(Entity entity, float interp, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx, shaders_fixer$lby);
    }
    
    @Inject(method = "renderSwirl",
            at = @At(value = "HEAD"), remap = false)
    public void renderSwirl(Entity entity, float interp, CallbackInfo ci) {
        shaders_fixer$lbx2 = Utils.GetLastBrightnessX();
        shaders_fixer$lby2 = Utils.GetLastBrightnessY();
        Utils.EnableFullBrightness();
    }
    
    @Inject(method = "renderSwirl",
            at = @At(value = "TAIL"), remap = false)
    public void renderSwirl2(Entity entity, float interp, CallbackInfo ci) {
        Utils.DisableFullBrightness(shaders_fixer$lbx2, shaders_fixer$lby2);
    }
    
    
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "renderJets",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderJetsPR(Entity entity, float interp, CallbackInfo ci) {
        shaders_fixer$program = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(method = "renderJets",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", shift = At.Shift.AFTER))
    public void renderJetsPRE(Entity entity, float interp, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
    }

    @Inject(method = "renderJets",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderJets(Entity entity, float interp, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    @Inject(method = "renderFlare",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void renderFlare(Entity entity, CallbackInfo ci) {
        Utils.Fix();
    }
}
