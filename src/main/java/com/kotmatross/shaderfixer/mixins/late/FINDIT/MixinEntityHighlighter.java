package com.kotmatross.shaderfixer.mixins.late.FINDIT;

import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnh.findit.fx.EntityHighlighter;
import com.kotmatross.shaderfixer.utils.Utils;

@Mixin(value = EntityHighlighter.class, priority = 999)
public class MixinEntityHighlighter {

    @Inject(
        method = "renderHighlightedEntities",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 1),
        remap = false)
    public void renderHighlightedEntities(RenderWorldLastEvent event, CallbackInfo ci) {
        Utils.fix();
    }

}
