package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.blocks.generic.BlockBobble;
import com.hbm.render.tileentity.RenderBobble;
import com.kotmatross.shadersfixer.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderBobble.class, priority = 999)
public class MixinRenderBobble {
    @Inject(method = "renderPellet", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    private void renderPellet(BlockBobble.BobbleType type, CallbackInfo ci) {
        Utils.Fix();
    }
}
