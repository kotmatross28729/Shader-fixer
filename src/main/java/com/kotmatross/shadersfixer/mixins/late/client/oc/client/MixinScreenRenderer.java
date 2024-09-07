package com.kotmatross.shadersfixer.mixins.late.client.oc.client;

import com.kotmatross.shadersfixer.Utils;
import li.cil.oc.client.renderer.tileentity.ScreenRenderer$;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ScreenRenderer$.class, priority = 999)
public class MixinScreenRenderer {
//THE FUCK SCALA ADDS THIS FUCKING "$" -  JUST TO BREAK ALL THE METHODS ACCESS???
   @Inject(method = "draw", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslated(DDD)V", shift = At.Shift.AFTER), remap = false)
   private void draw(CallbackInfo ci) {
       Utils.Fix();
       Utils.EnableFullBrightness();
   }
}
