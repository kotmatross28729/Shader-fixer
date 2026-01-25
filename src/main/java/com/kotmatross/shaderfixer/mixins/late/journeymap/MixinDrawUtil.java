package com.kotmatross.shaderfixer.mixins.late.journeymap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import journeymap.client.forge.helper.IRenderHelper;
import journeymap.client.render.draw.DrawUtil;

@Mixin(value = DrawUtil.class, priority = 999)
public class MixinDrawUtil {

    // Fix alpha > 1
    @Redirect(
        method = "drawQuad(Ljourneymap/client/render/texture/TextureImpl;DDDDDLjava/lang/Integer;FZZIIZZ)V",
        at = @At(value = "INVOKE", target = "Ljourneymap/client/forge/helper/IRenderHelper;glColor4f(FFFF)V"),
        remap = false)
    private static void transformGLColor(IRenderHelper instance, float r, float g, float b, float a) {
        instance.glColor4f(r, g, b, Math.min(a, 1F));
    }

}
