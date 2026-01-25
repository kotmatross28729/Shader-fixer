// package com.kotmatross.shaderfixer.mixins.late.SCHEMATICA;
//
// import org.spongepowered.asm.mixin.Mixin;
// import org.spongepowered.asm.mixin.injection.At;
// import org.spongepowered.asm.mixin.injection.Inject;
// import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
// import com.kotmatross.shaderfixer.utils.ShaderUtils;
// import com.llamalad7.mixinextras.sugar.Share;
// import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
//
// @Mixin(targets = "com.github.lunatrius.schematica.client.renderer.RendererSchematicGlobal", priority = 999)
// public class MixinRendererSchematicGlobal {
//
// @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
// public void render(CallbackInfo ci) {
// ShaderUtils.enableFullBrightness();
// ShaderUtils.fix();
// }
//
// @Inject(method = "render", at = @At(value = "TAIL"), remap = false)
// public void render2(CallbackInfo ci) {
// ShaderUtils.disableFullBrightness();
// }
//
// @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
// public void render$programS(CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
// shader_fixer$program.set(ShaderUtils.getCurrentProgram());
// ShaderUtils.useDefaultProgram();
// }
//
// @Inject(method = "render", at = @At(value = "TAIL"), remap = false)
// public void render$programE(CallbackInfo ci, @Share("shader_fixer$program") LocalIntRef shader_fixer$program) {
// ShaderUtils.useProgram(shader_fixer$program.get());
// }
// }
