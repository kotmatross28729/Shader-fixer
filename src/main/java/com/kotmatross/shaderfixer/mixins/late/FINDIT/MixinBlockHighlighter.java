package com.kotmatross.shaderfixer.mixins.late.FINDIT;

import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnh.findit.fx.BlockHighlighter;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = BlockHighlighter.class, priority = 999)
public class MixinBlockHighlighter {

    @Inject(
        method = "renderHighlightedBlock",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 1),
        remap = false)
    public void renderHighlightedBlock(RenderWorldLastEvent event, CallbackInfo ci) {
        Utils.Fix();
    }

}
