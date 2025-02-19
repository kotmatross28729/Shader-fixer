package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.dim.SkyProviderCelestial;
import com.kotmatross.shadersfixer.AngelicaUtils;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SkyProviderCelestial.class, priority = 999)
public class MixinSkyProviderCelestial {
    //FOR NTM:SPACE
    
    //TODO: 
    // * Find out why complementary doesn’t like alpha shenanigans (sometimes too much, sometimes not at all)
    // * What the hell is going on orbit (if it's even possible)
    
    
   //Fix sky with shaders
   @Inject(method = "render",
        at = @At(value = "HEAD"), remap = false)
    public void render(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.Fix2();
    }
    
    @Redirect(method = "render",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 1), remap = false)
    private void transformGLColor(float r, float g, float b, float a) {
      float alpha = a;
      
      //Because with shaders: alpha < 0.1 = full alpha, alpha > 1 = you can go blind
      if(AngelicaUtils.isShaderEnabled()) {
          alpha = MathHelper.clamp_float(alpha, 0.1F, 1.0F);
      }
       
       GL11.glColor4f(r, g, b, alpha);
    }
    
    //Disable sunset (bugged with complementary - blinding white bar)
    @WrapWithCondition(
            method = "render",
            at = @At(value = "INVOKE", target = "Lcom/hbm/dim/SkyProviderCelestial;renderSunset(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;)V")
            , remap = false
    )
    private boolean disableSunset(SkyProviderCelestial instance, float partialTicks, WorldClient world, Minecraft mc) {
        return !AngelicaUtils.isShaderEnabled();
    }
    
    //!DIRTY HACK
    /**
     *  All these WrapWithConditions are aimed at removing this block of code:
     *  <pre>
     *  {@code
     *          tessellator.func_78382_b();
     *          tessellator.func_78374_a(-115.0 * sc, 100.0, -115.0 * sc, 0.0 + uvOffset, 0.0);
     *          tessellator.func_78374_a(115.0 * sc, 100.0, -115.0 * sc, 1.0 + uvOffset, 0.0);
     *          tessellator.func_78374_a(115.0 * sc, 100.0, 115.0 * sc, 1.0 + uvOffset, 1.0);
     *          tessellator.func_78374_a(-115.0 * sc, 100.0, 115.0 * sc, 0.0 + uvOffset, 1.0);
     *          tessellator.func_78381_a();
     *  }
     * </pre>
     *  If angelica shaders are enabled AND planet have at least 0.1 alpha 
     *  (with complementary the planet is rendered completely black, so we don't render it while the player is on the ground (to not ruin the horizon))
     */
    
    @WrapWithCondition(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", ordinal = 1)
    )
    private boolean disableStartDrawingQuadsRender(Tessellator instance, @Local(ordinal = 2) Vec3 pos) {
        if(AngelicaUtils.isShaderEnabled()){
            return ((float) pos.yCoord - 200.0F) / 300.0F > 0.1;
        }
        return true;
    }
    @WrapWithCondition(method = "render",
            slice = @Slice(from = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                    ordinal = 0),
                    to = @At(value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                            ordinal = 1)),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V"))
    private boolean disableAddVertexWithUVRender(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_, double p_78374_7_, double p_78374_9_, @Local(ordinal = 2) Vec3 pos) {
        if(AngelicaUtils.isShaderEnabled()){
            return ((float) pos.yCoord - 200.0F) / 300.0F > 0.1;
        }
        return true;
    }
    @WrapWithCondition(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1)
    )
    private boolean disableDrawRender(Tessellator instance, @Local(ordinal = 2) Vec3 pos) {
        if(AngelicaUtils.isShaderEnabled()){
            return ((float) pos.yCoord - 200.0F) / 300.0F > 0.1;
        }
        return true;
    }
    
    /**
    *  All these WrapWithConditions are aimed at removing this block of code:
    *  <pre>
    *  {@code
    *        tessellator.func_78382_b();
    *        tessellator.func_78377_a(-sunSize, 99.9, -sunSize);
    *        tessellator.func_78377_a(sunSize, 99.9, -sunSize);
    *        tessellator.func_78377_a(sunSize, 99.9, sunSize);
    *        tessellator.func_78377_a(-sunSize, 99.9, sunSize);
    *        tessellator.func_78381_a();
    *  }
    * </pre>
    *  If angelica shaders are enabled (fixes the white square near the sun)
    */
    
    @WrapWithCondition(
            method = "renderSun",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V", ordinal = 1)
    )
    private boolean disableStartDrawingQuads(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled();
    }
    @WrapWithCondition(method = "renderSun",
            slice = @Slice(from = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;addVertex(DDD)V",
                    ordinal = 0),
                    to = @At(value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
                            ordinal = 1)),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;addVertex(DDD)V"))
    private boolean disableAddVertex(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_) {
        return !AngelicaUtils.isShaderEnabled();
    }
    @WrapWithCondition(
            method = "renderSun",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1)
    )
    private boolean disableDraw(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled();
    }
}

