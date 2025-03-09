package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.dim.CelestialBody;
import com.hbm.dim.SkyProviderCelestial;
import com.hbm.dim.SolarSystem;
import com.kotmatross.shadersfixer.AngelicaUtils;
import com.kotmatross.shadersfixer.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

@Mixin(value = SkyProviderCelestial.class, priority = 999)
public class MixinSkyProviderCelestial {
    // FOR NTM:SPACE
    // !Sensitive to changes

    // TODO:
    // Planet render works on moho, even with complementary (getSunBrightness related?)
    // Make sun bright (On moho (late sunset-night), it just appears as a floating white square, Utils)

    // Fix sky with shaders
    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void render(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.Fix2();
    }

    // Fixes sunset not rendering
    @Unique
    public int shaders_fixer$programSUNSET;

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/dim/SkyProviderCelestial;renderSunset(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;)V",
            shift = At.Shift.BEFORE),
        remap = false)
    public void SunsetOPERATION(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        shaders_fixer$programSUNSET = Utils.GLGetCurrentProgram();
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/dim/SkyProviderCelestial;renderSunset(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;)V",
            shift = At.Shift.AFTER),
        remap = false)
    public void SunsetOPERATION2(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$programSUNSET);
    }

    // Because with shaders: alpha < 0.1 = full alpha, alpha > 1 = you can go blind
    @Redirect(
        method = "render",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 1),
        remap = false)
    private void transformGLColor(float r, float g, float b, float a) {
        GL11.glColor4f(r, g, b, AngelicaUtils.isShaderEnabled() ? MathHelper.clamp_float(a, 0.1F, 1.0F) : a);
    }

    // glSkyList2 is ignored by shaders anyway.
    // Disabling it fixes sunset overlap
    @WrapWithCondition(
        method = "render",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glCallList(I)V", ordinal = 2),
        remap = false)
    private boolean disableCallList2Shaders(int i) {
        return !AngelicaUtils.isShaderEnabled();
    }

    // Idfk how, but it also disables "planet" (giant black square) rendering with complementary
    // A more painless way, instead of completely deleting the render with all shaders
    @WrapWithCondition(
        method = "render",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBlendFunc(II)V", ordinal = 0),
        remap = false)
    private boolean disableBlendShaders(int i, int j) {
        return !AngelicaUtils.isShaderEnabled();
    }

    // Offsets Tessellator's y by 0.1, preventing z-fighting with shader skybox
    @ModifyArg(
        method = "renderSun",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                ordinal = 8),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 3)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V"),
        index = 1)
    private double fixSunZFighting(double original) {
        return AngelicaUtils.isShaderEnabled() ? original - 0.1D : original;
    }

    // Offset y for flare accordingly
    @ModifyArg(
        method = "renderSun",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                ordinal = 12),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 4)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V"),
        index = 1)
    private double fixSunZFighting2(double original) {
        return AngelicaUtils.isShaderEnabled() ? original - 0.1D : original;
    }

    /**
     * All these WrapWithConditions are aimed at removing this block of code:
     * 
     * <pre>
     *  {@code
     *        tessellator.func_78382_b();
     *        tessellator.func_78377_a(-sunSize, 99.9, -sunSize);
     *        tessellator.func_78377_a(sunSize, 99.9, -sunSize);
     *        tessellator.func_78377_a(sunSize, 99.9, sunSize);
     *        tessellator.func_78377_a(-sunSize, 99.9, sunSize);
     *        tessellator.func_78381_a();
     *  }
     * </pre>
     * 
     * If angelica shaders are enabled (fixes the white square near the sun)
     */

    @WrapWithCondition(
        method = "renderSun",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 1))
    private boolean disableStartDrawingQuads(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled();
    }

    @WrapWithCondition(
        method = "renderSun",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertex(DDD)V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertex(DDD)V"))
    private boolean disableAddVertex(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_) {
        return !AngelicaUtils.isShaderEnabled();
    }

    @WrapWithCondition(
        method = "renderSun",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1))
    private boolean disableDraw(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled();
    }

    /**
     * All these WrapWithConditions are aimed at removing this block of code:
     * 
     * <pre>
     *  {@code
     * tessellator.func_78382_b();
     * tessellator.func_78374_a(-size, 100.0, -size, 0.0, 0.0);
     * tessellator.func_78374_a(size, 100.0, -size, 1.0, 0.0);
     * tessellator.func_78374_a(size, 100.0, size, 1.0, 1.0);
     * tessellator.func_78374_a(-size, 100.0, size, 0.0, 1.0);
     * tessellator.func_78381_a();
     *  }
     * </pre>
     * 
     * If angelica shaders are enabled AND rendering planet is on orbit (Fixes the horror that is happening in orbit)
     */
    @Unique
    public CelestialBody shaders_fixer$GET_IS_ORBIT;

    @Inject(method = "renderCelestials", at = @At(value = "HEAD"), remap = false)
    public void GET_ORBIT(float partialTicks, WorldClient world, Minecraft mc, List<SolarSystem.AstroMetric> metrics,
        float celestialAngle, CelestialBody tidalLockedBody, Vec3 planetTint, float visibility, float blendAmount,
        CelestialBody orbiting, float maxSize, CallbackInfo ci) {
        shaders_fixer$GET_IS_ORBIT = orbiting;
    }

    @WrapWithCondition(
        method = "renderCelestials",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 2))
    private boolean disableStartDrawingQuadsORBIT(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT == null);
    }

    @WrapWithCondition(
        method = "renderCelestials",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                ordinal = 8),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 2)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V"))
    private boolean disableAddVertexWithUVORBIT(Tessellator instance, double p_78377_1_, double p_78377_3_,
        double p_78377_5_, double p_78374_7_, double p_78374_9_) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT == null);
    }

    @WrapWithCondition(
        method = "renderCelestials",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 2))
    private boolean disableDrawORBIT(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT == null);
    }
}
