package com.kotmatross.shaderfixer.utils.ntm;

import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.tileentity.RenderCatalyticCracker;
import com.hbm.render.tileentity.RenderChimneyBrick;
import com.hbm.render.tileentity.RenderChimneyIndustrial;
import com.hbm.render.tileentity.RenderCoker;
import com.hbm.render.tileentity.RenderFrackingTower;
import com.kotmatross.shaderfixer.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import java.util.HashMap;
import java.util.Map;

public enum ShadowModel {
	
	CHIMNEY_BRICK("chimney_brick", RenderChimneyBrick.class),
	CHIMNEY_INDUSTRIAL("chimney_industrial", RenderChimneyIndustrial.class),
	COKER("coker", RenderCoker.class),
	CRACKING_TOWER("cracking_tower", RenderCatalyticCracker.class),
	FRACKING_TOWER("fracking_tower", RenderFrackingTower.class)
			
	;
	
	private final String name;
	private final Class<?> targetClass;
	
	private IModelCustom s2048;
	private IModelCustom s4096;
	private IModelCustom s8192;
	
	ShadowModel(String name, Class<?> targetClass) {
		this.name = name;
		this.targetClass = targetClass;
	}

	private static final Map<Class<?>, ShadowModel> CLASS_MAP = new HashMap<>();
	
	static {
		for (ShadowModel model : values()) {
			CLASS_MAP.put(model.targetClass, model);
		}
	}
	
	public static ShadowModel getByClass(Class<?> renderClass) {
		return CLASS_MAP.get(renderClass);
	}
	
	public void init() {
		this.s2048 = loadModel(2048);
		this.s4096 = loadModel(4096);
		this.s8192 = loadModel(8192);
	}
	
	private IModelCustom loadModel(int res) {
		ResourceLocation loc = new ResourceLocation(
				Tags.MODID, "models/ntm_selfshadowing_fix/" + res + "/" + this.name + ".obj");
		return new HFRWavefrontObject(loc).asVBO();
	}
	
	public IModelCustom getModel(int shadowRes, IModelCustom defInstance) {
		if (shadowRes <= 2048) return this.s2048;
		if (shadowRes <= 4096) return this.s4096;
		if (shadowRes <= 8192) return this.s8192;
		return defInstance;
	}
	
}
