package com.kotmatross.shaderfixer.mixins.late.HBM.space;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.dim.CelestialBody;
import com.hbm.dim.SkyProviderCelestial;
import com.hbm.dim.SolarSystem;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;
import com.kotmatross.shaderfixer.shrimp.SPEKJORK;
import com.kotmatross.shaderfixer.utils.AngelicaUtils;
import com.kotmatross.shaderfixer.utils.Utils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

@SPEKJORK
@Mixin(value = SkyProviderCelestial.class, priority = 999)
public class MixinSkyProviderCelestial {

    // !Sensitive to upstream(?) changes

    // Fix sky with shaders
    @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
    public void initSkyFix(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
        Utils.Fix2();
    }

    // Fixes sunset not rendering
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/dim/SkyProviderCelestial;renderSunset(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;)V",
            shift = At.Shift.BEFORE),
        remap = false)
    public void sunset_PF_Start(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci,
        @Share("shaders_fixer$programSUNSET") LocalIntRef shaders_fixer$programSUNSET) {
        shaders_fixer$programSUNSET.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/hbm/dim/SkyProviderCelestial;renderSunset(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;)V",
            shift = At.Shift.AFTER),
        remap = false)
    public void sunset_PF_End(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci,
        @Share("shaders_fixer$programSUNSET") LocalIntRef shaders_fixer$programSUNSET) {
        Utils.GLUseProgram(shaders_fixer$programSUNSET.get());
    }

    // glSkyList2 is ignored by shaders anyway
    // Disabling it fixes sunset overlap
    // Oh, and I fucking hate this thing
    @WrapWithCondition(
        method = "render",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glCallList(I)V", ordinal = 2),
        remap = false)
    private boolean disableFuckingHorizon(int i) {
        return !ShaderFixerConfig.VANILLA_DISABLE_HORIZON;
    }

    /**
     * All these WrapWithConditions are aimed at removing this block of code:
     *
     * <pre>
     *  {@code
     * tessellator.startDrawingQuads();
     * tessellator.addVertexWithUV(-115 * sc, 100.0D, -115 * sc, 0.0D + uvOffset, 0.0D);
     * tessellator.addVertexWithUV(115 * sc, 100.0D, -115 * sc, 1.0D + uvOffset, 0.0D);
     * tessellator.addVertexWithUV(115 * sc, 100.0D, 115 * sc, 1.0D + uvOffset, 1.0D);
     * tessellator.addVertexWithUV(-115 * sc, 100.0D, 115 * sc, 0.0D + uvOffset, 1.0D);
     * tessellator.draw();
     *  }
     * </pre>
     *
     * If angelica shaders are enabled (Removes the rendering of the planet below the player at high altitude)
     */

    // Inject where `GL11.glColor4f(sunBrightness, sunBrightness, sunBrightness, ((float)pos.yCoord - 200.0F) /
    // 300.0F);`

    // Maybe I'll fix this... someday.

    @WrapWithCondition(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 1))
    private boolean removePlanetRender0(Tessellator instance) {
        return !ShaderFixerConfig.NTM_SPACE_DISABLE_PLANET_RENDER && !AngelicaUtils.isShaderEnabled();
    }

    @WrapWithCondition(
        method = "render",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                ordinal = 0),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertex(DDD)V"))
    private boolean removePlanetRender1(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_,
        double p_78374_7_, double p_78374_9_) {
        return !ShaderFixerConfig.NTM_SPACE_DISABLE_PLANET_RENDER && !AngelicaUtils.isShaderEnabled();
    }

    @WrapWithCondition(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1))
    private boolean removePlanetRender2(Tessellator instance) {
        return !ShaderFixerConfig.NTM_SPACE_DISABLE_PLANET_RENDER && !AngelicaUtils.isShaderEnabled();
    }

    @Inject(
        method = "renderSun",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/renderer/texture/TextureManager.func_110577_a (Lnet/minecraft/util/ResourceLocation;)V",
            shift = At.Shift.BEFORE,
            ordinal = 1),
        remap = false)
    public void SUN_BRIGHT(float partialTicks, WorldClient world, Minecraft mc, CelestialBody sun, double sunSize,
        double coronaSize, float visibility, float pressure, CallbackInfo ci) {
        Utils.EnableFullBrightness();
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
        return AngelicaUtils.isShaderEnabled() ? original - 0.3D : original;
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
    private double fixSunFlareZFighting(double original) {
        return AngelicaUtils.isShaderEnabled() ? original - 0.3D : original;
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
    private boolean fixWhiteSquare0(Tessellator instance) {
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
    private boolean fixWhiteSquare1(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_) {
        return !AngelicaUtils.isShaderEnabled();
    }

    @WrapWithCondition(
        method = "renderSun",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 1))
    private boolean fixWhiteSquare2(Tessellator instance) {
        return !AngelicaUtils.isShaderEnabled();
    }

    /**
     * Here:
     *
     * <pre>
     *  {@code
     *   if(metric.body == tidalLockedBody) {
     *   GL11.glRotated(celestialAngle * -360.0 - 60.0, 1.0, 0.0, 0.0);
     *   } else {
     *   GL11.glRotated(metric.angle, 1.0, 0.0, 0.0);
     *   }
     *   GL11.glRotatef(axialTilt + 90.0F, 0.0F, 1.0F, 0.0F);
     *   
     *   
     *   INJECT(shaders_fixer$programORBIT.set(Utils.GLGetCurrentProgram()), Utils.GLUseDefaultProgram())
     *   
     *   tessellator.startDrawingQuads();
     *   tessellator.addVertexWithUV(-size, 100.0D, -size, 0.0D + uvOffset, 0.0D);
     *   tessellator.addVertexWithUV(size, 100.0D, -size, 1.0D + uvOffset, 0.0D);
     *   tessellator.addVertexWithUV(size, 100.0D, size, 1.0D + uvOffset, 1.0D);
     *   tessellator.addVertexWithUV(-size, 100.0D, size, 0.0D + uvOffset, 1.0D);
     *   tessellator.draw();
     *   
     *   INJECT(Utils.GLUseProgram(shaders_fixer$programORBIT.get()))
     *   
     *  }
     * </pre>
     *
     * Fixes orbit... for fucking 2 time already
     * 
     */

    @Inject(
        method = "renderCelestials",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 0,
            shift = At.Shift.BEFORE))
    private void fixOrbitProgramStart(float partialTicks, WorldClient world, Minecraft mc,
        List<SolarSystem.AstroMetric> metrics, float celestialAngle, CelestialBody tidalLockedBody, Vec3 planetTint,
        float visibility, float blendAmount, CelestialBody orbiting, float maxSize, CallbackInfo ci,
        @Share("shaders_fixer$programORBIT") LocalIntRef shaders_fixer$programORBIT) {
        shaders_fixer$programORBIT.set(Utils.GLGetCurrentProgram());
        Utils.GLUseDefaultProgram();
    }

    @Inject(
        method = "renderCelestials",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;draw()I",
            ordinal = 0,
            shift = At.Shift.AFTER))
    private void fixOrbitProgramEnd(float partialTicks, WorldClient world, Minecraft mc,
        List<SolarSystem.AstroMetric> metrics, float celestialAngle, CelestialBody tidalLockedBody, Vec3 planetTint,
        float visibility, float blendAmount, CelestialBody orbiting, float maxSize, CallbackInfo ci,
        @Share("shaders_fixer$programORBIT") LocalIntRef shaders_fixer$programORBIT) {
        Utils.GLUseProgram(shaders_fixer$programORBIT.get());
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

    // Inject where "Draw another layer on top to blend with the atmosphere"

    @Inject(method = "renderCelestials", at = @At(value = "HEAD"), remap = false)
    public void GET_ORBIT(float partialTicks, WorldClient world, Minecraft mc, List<SolarSystem.AstroMetric> metrics,
        float celestialAngle, CelestialBody tidalLockedBody, Vec3 planetTint, float visibility, float blendAmount,
        CelestialBody orbiting, float maxSize, CallbackInfo ci,
        @Share("shaders_fixer$GET_IS_ORBIT") LocalRef<CelestialBody> shaders_fixer$GET_IS_ORBIT) {
        shaders_fixer$GET_IS_ORBIT.set(orbiting);
    }

    @WrapWithCondition(
        method = "renderCelestials",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V",
            ordinal = 5))
    private boolean fixOrbitBlendLayer0(Tessellator instance,
        @Share("shaders_fixer$GET_IS_ORBIT") LocalRef<CelestialBody> shaders_fixer$GET_IS_ORBIT) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT.get() == null);
    }

    @WrapWithCondition(
        method = "renderCelestials",
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V",
                ordinal = 20),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 5)),
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV(DDDDD)V"))
    private boolean fixOrbitBlendLayer1(Tessellator instance, double p_78377_1_, double p_78377_3_, double p_78377_5_,
        double p_78374_7_, double p_78374_9_,
        @Share("shaders_fixer$GET_IS_ORBIT") LocalRef<CelestialBody> shaders_fixer$GET_IS_ORBIT) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT.get() == null);
    }

    @WrapWithCondition(
        method = "renderCelestials",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw()I", ordinal = 5))
    private boolean fixOrbitBlendLayer2(Tessellator instance,
        @Share("shaders_fixer$GET_IS_ORBIT") LocalRef<CelestialBody> shaders_fixer$GET_IS_ORBIT) {
        return !AngelicaUtils.isShaderEnabled() || (shaders_fixer$GET_IS_ORBIT.get() == null);
    }
}
