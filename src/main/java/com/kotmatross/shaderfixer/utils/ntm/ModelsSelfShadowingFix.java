package com.kotmatross.shaderfixer.utils.ntm;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import com.hbm.render.loader.HFRWavefrontObject;
import com.kotmatross.shaderfixer.Tags;

public class ModelsSelfShadowingFix {

    public static final IModelCustom chimney_brick = new HFRWavefrontObject(
        new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/chimney_brick.obj")).asVBO();
    public static final IModelCustom chimney_industrial = new HFRWavefrontObject(
        new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/chimney_industrial.obj")).asVBO();
    public static final IModelCustom coker = new HFRWavefrontObject(
        new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/coker.obj")).asVBO();

}
