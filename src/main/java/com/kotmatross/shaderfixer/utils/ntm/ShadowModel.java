package com.kotmatross.shaderfixer.utils.ntm;

import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.tileentity.RenderCatalyticCracker;
import com.hbm.render.tileentity.RenderChimneyBrick;
import com.hbm.render.tileentity.RenderChimneyIndustrial;
import com.hbm.render.tileentity.RenderCoker;
import com.hbm.render.tileentity.RenderFrackingTower;
import com.hbm.render.tileentity.RenderLargeTower;
import com.hbm.render.tileentity.RenderSmallTower;
import com.kotmatross.shaderfixer.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import java.util.HashMap;
import java.util.Map;

public enum ShadowModel {
	
	CHIMNEY_BRICK("chimney_brick", RenderChimneyBrick.class),
	CHIMNEY_INDUSTRIAL("chimney_industrial", RenderChimneyIndustrial.class),
	COKER("coker", RenderCoker.class),
	CATALYTIC_CRACKER("catalytic_cracker", RenderCatalyticCracker.class),
	FRACKING_TOWER("fracking_tower", RenderFrackingTower.class),
	TOWER_LARGE("tower_large", RenderLargeTower.class),
	TOWER_SMALL("tower_small", RenderSmallTower.class),
	
	;
	
	private final String name;
	private final Class<?> targetClass;
	private IModelCustom model;
	
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
		ResourceLocation loc = new ResourceLocation(
				Tags.MODID, "models/ntm_selfshadowing_fix/" + this.name + ".obj");
		this.model = new HFRWavefrontObject(loc).asVBO();
	}

	public IModelCustom getModel() {
		return this.model;
	}
	
}
