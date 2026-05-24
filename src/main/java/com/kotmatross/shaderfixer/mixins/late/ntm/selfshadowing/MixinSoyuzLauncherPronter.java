package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import com.hbm.main.ResourceManager;
import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.util.SoyuzLauncherPronter;
import com.kotmatross.shaderfixer.Tags;
import com.kotmatross.shaderfixer.utils.angelica.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ShadowModel;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = SoyuzLauncherPronter.class, priority = 999, remap = false)
public class MixinSoyuzLauncherPronter {
	
	//todo: what the fuck did I make
	
	private static IModelCustom soyuz_launcher_support = new HFRWavefrontObject(
			new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/soyuz_launcher_support.obj")).asVBO();
	private static IModelCustom soyuz_launcher_table = new HFRWavefrontObject(
			new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/soyuz_launcher_table.obj")).asVBO();
	private static IModelCustom soyuz_launcher_tower = new HFRWavefrontObject(
			new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/soyuz_launcher_tower.obj")).asVBO();
	
	
	@ModifyReceiver(method = "prontLauncher"
			, at = @At(value = "INVOKE"
				, target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V"
				, remap = true))
	private static IModelCustom fixModel(IModelCustom instance) {
		if (AngelicaUtils_WRAPPER.isShadowPass()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
				if(instance == ResourceManager.soyuz_launcher_table)
					return soyuz_launcher_table;
				if(instance == ResourceManager.soyuz_launcher_tower)
					return soyuz_launcher_tower;
				if(instance == ResourceManager.soyuz_launcher_support)
					return soyuz_launcher_support; //todo: fix this shit, I forgot inner part
			}
			
		}
		return instance;
	}
	
}
