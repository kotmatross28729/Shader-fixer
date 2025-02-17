package com.kotmatross.shadersfixer;

import net.irisshaders.iris.api.v0.IrisApi;

public class AngelicaUtils {
	public static boolean isShaderEnabled(){
		return IrisApi.getInstance().isShaderPackInUse();
	}
}
