package com.kotmatross.shadersfixer.mixins.late.client.Journeymap;

import com.kotmatross.shadersfixer.Utils;
import journeymap.client.forge.helper.IRenderHelper;
import journeymap.client.render.draw.DrawUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DrawUtil.class, priority = 999)
public class MixinDrawUtil {

    @Inject(method = "drawRectangle", at = @At(value = "HEAD"), remap = false)
    private static void drawRectangle(double x, double y, double width, double height, int color, int alpha, CallbackInfo ci) {
        Utils.Fix();
    }
    
    //Fix alpha > 1
    @Redirect(method = "drawQuad(Ljourneymap/client/render/texture/TextureImpl;DDDDDLjava/lang/Integer;FZZIIZZ)V",
            at = @At(value = "INVOKE", target = "Ljourneymap/client/forge/helper/IRenderHelper;glColor4f(FFFF)V"), remap = false)
    private static void transformGLColor(IRenderHelper instance, float r, float g, float b, float a) {
        instance.glColor4f(r,g,b,Math.min(a, 1F));
    }
    
}
