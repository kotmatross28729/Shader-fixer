package com.kotmatross.shaderfixer.utils.ntm;

import com.hbm.render.tileentity.RenderDysonLauncher;
import net.minecraftforge.client.model.IModelCustom;

public class ModelsSelfShadowingFixSpace {
	
	//todo: actual fix
	public static IModelCustom dyson_spinlaunch;
	
	public static void init() {
		dyson_spinlaunch = ModelsSelfShadowingFix.constructModel("space/dyson_spinlaunch", RenderDysonLauncher.class);
	}
	
}
