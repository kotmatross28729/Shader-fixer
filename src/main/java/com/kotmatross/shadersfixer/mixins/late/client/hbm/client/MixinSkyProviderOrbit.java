package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.dim.orbit.SkyProviderOrbit;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SkyProviderOrbit.class, priority = 999)
public class MixinSkyProviderOrbit {
	//FOR NTM:SPACE
	
	//TODO: 
	// * What the hell is going on orbit (if it's even possible)
	
	@Unique
	public int shaders_fixer$programPLANET;
	
	@Inject(method = "render",
			at = @At(value = "INVOKE",
					target = "Lcom/hbm/dim/SkyProviderCelestial;renderCelestials(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;Ljava/util/List;FLcom/hbm/dim/CelestialBody;Lnet/minecraft/util/Vec3;FFLcom/hbm/dim/CelestialBody;F)V",
					shift = At.Shift.BEFORE), remap = false
			)
	public void PlanetOPERATION(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
		shaders_fixer$programPLANET = Utils.GLGetCurrentProgram();
		Utils.GLUseDefaultProgram();
	}
	
	@Inject(method = "render",
			at = @At(value = "INVOKE",
					target = "Lcom/hbm/dim/SkyProviderCelestial;renderCelestials(FLnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/client/Minecraft;Ljava/util/List;FLcom/hbm/dim/CelestialBody;Lnet/minecraft/util/Vec3;FFLcom/hbm/dim/CelestialBody;F)V",
					shift = At.Shift.AFTER), remap = false
			)
	public void PlanetOPERATION2(float partialTicks, WorldClient world, Minecraft mc, CallbackInfo ci) {
		Utils.GLUseProgram(shaders_fixer$programPLANET);
	}
}
