package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.pack.json.shape;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.Utils;

@Mixin(value = com.fiskmods.heroes.client.pack.json.shape.ShapeFormatLines.class, priority = 999)
public abstract class MixinShapeFormatLines implements com.fiskmods.heroes.client.pack.json.shape.IShapeFormat {

    @Inject(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V"))
    public void render(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
        float ticks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }

    // @Inject(
    // method = "render",
    // at = @At(
    // value = "INVOKE",
    // target = "Lnet/minecraft/client/renderer/Tessellator;startDrawing(I)V",
    // shift = At.Shift.BEFORE))
    // public void render_PF(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
    // float ticks, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
    // shaders_fixer$program.set(Utils.GLGetCurrentProgram());
    // Utils.GLUseDefaultProgram();
    // }
    //
    // @Inject(
    // method = "render",
    // at = @At(
    // value = "INVOKE",
    // target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
    // shift = At.Shift.AFTER))
    // public void render_PFE(com.fiskmods.heroes.client.pack.json.shape.JsonShape shape, Entity entity, float mult,
    // float ticks, CallbackInfo ci, @Share("shaders_fixer$program") LocalIntRef shaders_fixer$program) {
    // Utils.GLUseProgram(shaders_fixer$program.get());
    // }
}
