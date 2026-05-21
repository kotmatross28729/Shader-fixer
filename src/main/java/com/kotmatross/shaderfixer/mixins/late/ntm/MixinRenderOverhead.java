package com.kotmatross.shaderfixer.mixins.late.ntm;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.render.util.RenderOverhead;
import com.kotmatross.shaderfixer.utils.ShaderUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = RenderOverhead.class, priority = 999, remap = false)
public class MixinRenderOverhead {
    

	@Unique
    @SuppressWarnings("AddedMixinMembersNamePattern")
    private static final String drawTagDesc = "drawTag(FDLjava/lang/String;DDDIZII)V";
    
    @WrapWithCondition(method = drawTagDesc
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glNormal3f(FFF)V"))
    private static boolean disableNormalsSetup(float nx, float ny, float nz) {
        // Peak mjoang coding v2
        return false;
    }

    @Inject(method = drawTagDesc
            , at = @At(value = "HEAD"))
    private static void drawTag(CallbackInfo ci) {
        ShaderUtils.enableFullBrightness();
    }

    @Inject(method = drawTagDesc
            , at = @At(value = "TAIL"))
    private static void drawTag2(CallbackInfo ci) {
        ShaderUtils.disableFullBrightness();
    }

    @Inject(method = drawTagDesc
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V"
                , shift = At.Shift.AFTER))
    private static void shader_fixer$AttribPush(CallbackInfo ci) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
    }

    @Inject(method = drawTagDesc
            , at = @At(value = "INVOKE"
                , target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V"
                , shift = At.Shift.BEFORE))
    private static void shader_fixer$AttribPop(CallbackInfo ci) {
        GL11.glPopAttrib();
    }

}
